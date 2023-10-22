package com.geoclass.backendtask.service;

import com.geoclass.backendtask.dto.StatusEnum;
import com.geoclass.backendtask.entities.AsyncJobEntity;
import com.geoclass.backendtask.entities.GeologicalClassEntity;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.exception.ExportErrorException;
import com.geoclass.backendtask.exception.ExportInProgressException;
import com.geoclass.backendtask.repositories.AsyncJobRepository;
import com.geoclass.backendtask.repositories.SectionRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ExportServiceImpl implements ExportService {

    final private SectionRepository sectionRepository;
    final private AsyncJobRepository asyncJobRepository;

    public ExportServiceImpl(SectionRepository sectionRepository, AsyncJobRepository asyncJobRepository) {
        this.sectionRepository = sectionRepository;
        this.asyncJobRepository = asyncJobRepository;
    }

    @Async

    public CompletableFuture<Long> exportXLSFile() throws IOException {
        String fileName;
        List<SectionEntity> sections = sectionRepository.findAll();
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long asyncId = new RandomDataGenerator().nextLong(leftLimit, rightLimit);
        AsyncJobEntity asyncJob = new AsyncJobEntity();
        asyncJob.setJobId(asyncId);
        asyncJob.setStatus(StatusEnum.IN_PROGRESS.getDisplayStatus());
        asyncJobRepository.save(asyncJob);

                

      //  Row dataRow;
           try( HSSFWorkbook workbook = new HSSFWorkbook()) {
               HSSFSheet sheet = workbook.createSheet("Exported Classes Data");
               int classNameCount = -1;
               for (SectionEntity section : sections) {
                   int count = section.getGeologicalClass().size();
                   if (count > classNameCount) {
                       classNameCount = count;
                   }
               }

               //Header Name Class name and Class code
               Row headerRow = sheet.createRow(0);
               Cell cell = headerRow.createCell(0);
               cell.setCellValue("Section Name");
               int i = 1, count = 1;
               while(i < classNameCount*2){
                   Cell classNameCell = headerRow.createCell(i);
                   classNameCell.setCellValue("Class "+ count + " Name");
                   Cell classCodeCell = headerRow.createCell(i+1);
                   classCodeCell.setCellValue("Class " + count + " Code");
                   i=i+2;
                   count++;
               }

               for (int k = 0; k < sections.size(); k++) {
                   SectionEntity section = sections.get(k);
                   Row dataRow = sheet.createRow(k + 1);
                   Cell sectionNameCell = dataRow.createCell(0);
                   sectionNameCell.setCellValue(section.getSectionName());

                   // Array to store class names and class codes
                   String[] classNames = new String[classNameCount];
                   String[] classCodes = new String[classNameCount];

                   for (GeologicalClassEntity className : section.getGeologicalClass()) {
                       if (!className.getClassName().isEmpty()) {
                           int classNum = Character.getNumericValue(className.getClassCode().charAt(className.getClassCode().length() - 1));
                           classNames[classNum - 1] = className.getClassName();
                           classCodes[classNum - 1] = className.getClassCode();
                       }
                   }

                   // Populate the row with class names and class codes
                   int colNum = 1;
                   for (int o = 0; o < classNameCount; o++) {
                       Cell insertClassNameData = dataRow.createCell(colNum++);
                       insertClassNameData.setCellValue(classNames[o] != null ? classNames[o] : "");
                       Cell insertClassCodeData = dataRow.createCell(colNum++);
                       insertClassCodeData.setCellValue(classCodes[o] != null ? classCodes[o] : "");
                   }
               }



               // Write the workbook to a file
               Date date = new Date();
               fileName = "Exported_" + date.getTime() +".xls";
               String filePath = "/Users/faizantahir/Documents/ProjectFiles/" + fileName;
               try (FileOutputStream fos = new FileOutputStream(filePath)) {
                   workbook.write(fos);
               }
           } catch (IOException e){
               AsyncJobEntity asyncJobErrorObject = ImportServiceImpl.asyncMethod(asyncId, asyncJobRepository);
               asyncJobErrorObject.setStatus(StatusEnum.ERROR.getDisplayStatus());
               asyncJobRepository.save(asyncJobErrorObject);
               throw new IOException(e);
           }


        AsyncJobEntity asyncJobDoneObject = ImportServiceImpl.asyncMethod(asyncId, asyncJobRepository);
        asyncJobDoneObject.setFileName(fileName);
        asyncJobRepository.save(asyncJobDoneObject);
        return CompletableFuture.completedFuture(asyncId);
    }

    public ResponseEntity<String> returnStatusOfJob(Long jobId){
        Optional<AsyncJobEntity> asyncJobOptional = asyncJobRepository.findByJobId(jobId);
        if (asyncJobOptional.isPresent()) {
            AsyncJobEntity asyncJob = asyncJobOptional.get();
            String status = asyncJob.getStatus();

            if (StatusEnum.DONE.getDisplayStatus().equals(status)) {
                return ResponseEntity.ok("DONE");
            } else if (StatusEnum.IN_PROGRESS.getDisplayStatus().equals(status)) {
                return ResponseEntity.ok("IN PROGRESS");
            } else if (status.startsWith(StatusEnum.ERROR.getDisplayStatus())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
            }
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<byte[]> downloadFile(Long jobId) throws IOException {
        Optional<AsyncJobEntity> asyncJobOptional = asyncJobRepository.findByJobId(jobId);
        if (asyncJobOptional.isPresent()) {
            AsyncJobEntity asyncJob = asyncJobOptional.get();
            String status = asyncJob.getStatus();
            String fileName = asyncJob.getFileName();

            if (StatusEnum.DONE.getDisplayStatus().equals(status)) {
                // Load and send the file if the status is "DONE"
                byte[] fileBytes = loadFile(fileName); // Implement this method to load the file
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=exported-file.xls");
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(fileBytes);
            } else if (StatusEnum.IN_PROGRESS.getDisplayStatus().equals(status)) {
                // Throw an exception if exporting is in progress
                throw new ExportInProgressException("Export is still in progress.");
            } else if (status.startsWith(StatusEnum.ERROR.getDisplayStatus())) {
                // Handle the error case
                throw new ExportErrorException("Export encountered an error: " + status);
            }
        }

        return ResponseEntity.notFound().build();
    }

    private byte[] loadFile(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("/Users/faizantahir/Documents/ProjectFiles/" + fileName));
    }

}