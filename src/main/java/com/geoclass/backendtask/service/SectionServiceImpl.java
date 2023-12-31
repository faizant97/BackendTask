package com.geoclass.backendtask.service;

import com.geoclass.backendtask.dto.CreateSectionDTO;
import com.geoclass.backendtask.dto.DeleteSectionDTO;
import com.geoclass.backendtask.dto.SectionResponseDTO;
import com.geoclass.backendtask.dto.UpdateSectionDTO;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SectionServiceImpl implements SectionService {



    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<SectionEntity> getSection() {

        List<SectionEntity> sectionEntityList ;
        sectionEntityList = sectionRepository.findAll();
        return sectionEntityList;

    }

    @Override
    public SectionResponseDTO createSection(CreateSectionDTO sectionDTO){
        SectionEntity sectionEntity = new SectionEntity();
        SectionResponseDTO sectionResponseDTO = new SectionResponseDTO();
        if(!sectionDTO.getName().isEmpty()) {
            sectionEntity.setSectionName(sectionDTO.getName());
            sectionEntity.setGeologicalClass(sectionDTO.getGeologicalClassEntityList());
            sectionEntity = sectionRepository.save(sectionEntity);
        }
        else {
            sectionEntity.setSectionName("Please Write a value");
        }

        sectionResponseDTO.setSectionName(sectionEntity.getSectionName());
        sectionResponseDTO.setGeologicalClassEntitySet(sectionEntity.getGeologicalClass());
        return sectionResponseDTO;
    }


    @Override
    public DeleteSectionDTO deleteSection(CreateSectionDTO createSectionDTO){
       DeleteSectionDTO deleteSectionDTO = new DeleteSectionDTO();
        SectionEntity sectionToBeDeleted;
        if(!createSectionDTO.getName().isEmpty()) {
            Optional<SectionEntity> result = search(createSectionDTO.getName());
            if(result.isPresent()){
                sectionToBeDeleted = result.get();
                sectionRepository.delete(sectionToBeDeleted);
                deleteSectionDTO.setSectionName(sectionToBeDeleted.getSectionName());
                deleteSectionDTO.setGeologicalClass(sectionToBeDeleted.getGeologicalClass());
                deleteSectionDTO.setMessage("DELETED");
                return  deleteSectionDTO;
                       // sectionToBeDeleted.getSectionName() + " is deleted!";
            }
        }
        deleteSectionDTO.setMessage("Section not found!");
        return deleteSectionDTO;

    }

    @Override
    public String updateSection(UpdateSectionDTO updateSectionDTO){
        SectionEntity sectionToBeUpdated;
        if (!updateSectionDTO.getExistingSectionName().isEmpty() && !updateSectionDTO.getUpdatedSectionName().isEmpty()){
            Optional<SectionEntity> result = search(updateSectionDTO.getExistingSectionName());
            if(result.isPresent()){
                sectionToBeUpdated = result.get();
                sectionToBeUpdated.setSectionName(updateSectionDTO.getUpdatedSectionName());

                sectionRepository.save(sectionToBeUpdated);
                return updateSectionDTO.getExistingSectionName() + " is updated to " + updateSectionDTO.getUpdatedSectionName();
            }
        }
        return "Please write existing and updated Section name!";
    }


    private Optional<SectionEntity> search(String name){
        List<SectionEntity>  sectionEntityList;
            sectionEntityList = sectionRepository.findAll();
            return sectionEntityList.stream()
                    .filter(item -> item.getSectionName().equals(name))
                    .findFirst();
        }

}