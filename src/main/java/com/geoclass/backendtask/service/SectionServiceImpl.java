package com.geoclass.backendtask.service;


import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.SectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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



}