package com.geoclass.backendtask.service;


import com.geoclass.backendtask.dto.CreateSectionDTO;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {



    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<SectionEntity> getSection() {

        List<SectionEntity> sectionEntityList;
        sectionEntityList = sectionRepository.findAll();
        return sectionEntityList;

    }

    @Override
    public SectionEntity createSection(CreateSectionDTO sectionDTO){
        SectionEntity sectionEntity = new SectionEntity();
        if(!sectionDTO.getName().isEmpty()) {
            sectionEntity.setSectionName(sectionDTO.getName());
            sectionEntity = sectionRepository.save(sectionEntity);
            return sectionEntity;
        }
        else {
            sectionEntity.setSectionName("Please Write a value");
            return sectionEntity;
        }
    }


    @Override
    public String deleteSection(CreateSectionDTO createSectionDTO){
       List<SectionEntity>  sectionEntityList;
       SectionEntity sectionToBeDeleted;
        if(!createSectionDTO.getName().isEmpty()) {
            sectionEntityList = sectionRepository.findAll();
            Optional<SectionEntity> result = sectionEntityList.stream()
                    .filter(item -> item.getSectionName().equals(createSectionDTO.getName()))
                    .findFirst();
            if(result.isPresent()){
                sectionToBeDeleted = result.get();
                sectionRepository.delete(sectionToBeDeleted);
                return  sectionToBeDeleted.getSectionName() + " is deleted!";
            }
        }
            return "Section not found!";
    }



}