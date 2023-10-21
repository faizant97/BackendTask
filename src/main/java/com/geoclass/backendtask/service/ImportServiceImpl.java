package com.geoclass.backendtask.service;


import com.geoclass.backendtask.dto.StatusEnum;
import com.geoclass.backendtask.entities.AsyncJobEntity;
import com.geoclass.backendtask.entities.GeologicalClassEntity;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.AsyncJobRepository;
import com.geoclass.backendtask.repositories.GeologicalClassRepository;
import com.geoclass.backendtask.repositories.SectionRepository;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ImportServiceImpl implements ImportService{


    private final GeologicalClassRepository geologicalClassRepository;
    private final SectionRepository sectionRepository;
    private final AsyncJobRepository asyncJobRepository;

    public ImportServiceImpl(GeologicalClassRepository geologicalClassRepository, SectionRepository sectionRepository, AsyncJobRepository asyncJobRepository) {
        this.geologicalClassRepository = geologicalClassRepository;
        this.sectionRepository = sectionRepository;
        this.asyncJobRepository = asyncJobRepository;
    }



    @Async
    @Transactional
    public CompletableFuture<Long> startImport(MultipartFile file) {

        long leftLimit = 1L;
        long rightLimit = 100000L;
        long asyncId = new RandomDataGenerator().nextLong(leftLimit, rightLimit);
        AsyncJobEntity asyncJob = new AsyncJobEntity();
        asyncJob.setJobId(asyncId);
        asyncJob.setStatus(StatusEnum.IN_PROGRESS.getDisplayStatus());
        asyncJobRepository.save(asyncJob);

        if(file.isEmpty()){
            AsyncJobEntity asyncJobEntity = asyncMethod(asyncId, asyncJobRepository);
            asyncJobEntity.setStatus(StatusEnum.ERROR.getDisplayStatus());
            asyncJobRepository.save(asyncJobEntity);
        }

        try(InputStream is = file.getInputStream();
            Workbook workbook = new HSSFWorkbook(is)){
            Sheet sheet = workbook.getSheetAt(0);
            for(Row row: sheet){
                // skipping header row
                if(row.getRowNum() == 0){
                    continue;
                }
                SectionEntity section = new SectionEntity();
                section.setSectionName(row.getCell(0).getStringCellValue());

                for (int cellIndex = 1; cellIndex < row.getLastCellNum(); cellIndex += 2 ){
                    if(row.getCell(cellIndex).getStringCellValue().isEmpty()){
                        continue;
                    }
                    else {
                        GeologicalClassEntity geologicalClassEntity =new GeologicalClassEntity();
                        Optional<Integer>  id = geologicalClassRepository.findByClassCode(row.getCell(cellIndex + 1).getStringCellValue());
                        id.ifPresent(geologicalClassEntity::setClassId);
                        section.getGeologicalClass().add(geologicalClassEntity);
                    }
                }
                sectionRepository.save(section);
            }
        }
        catch (RuntimeException | IOException e){
            asyncJob.setStatus(StatusEnum.ERROR.getDisplayStatus());
            asyncJobRepository.save(asyncJob);
            throw new  RuntimeException(e);
        }


        AsyncJobEntity asyncJobDoneObject = asyncMethod(asyncId, asyncJobRepository);
        asyncJobRepository.save(asyncJobDoneObject);
        return CompletableFuture.completedFuture(asyncId);
    }

    static AsyncJobEntity asyncMethod(long asyncId, AsyncJobRepository asyncJobRepository) {
        List<AsyncJobEntity> asyncJobList = asyncJobRepository.findAll();
        AsyncJobEntity asyncJobDoneObject = new AsyncJobEntity();
        for(AsyncJobEntity job: asyncJobList){
            if(job.getJobId().equals(asyncId)){
                asyncJobDoneObject = job;
            }
        }
        asyncJobDoneObject.setStatus(StatusEnum.DONE.getDisplayStatus());
        return asyncJobDoneObject;
    }


}
