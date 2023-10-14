package com.geoclass.backendtask.service;


import com.geoclass.backendtask.entities.sectionEntity;
import com.geoclass.backendtask.repositories.sectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class sectionServiceImpl implements sectionService {



    private final sectionRepository sectionRepository;

    public sectionServiceImpl(sectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<sectionEntity> getSection() {

        List<sectionEntity> sectionEntityList;


        sectionEntityList = sectionRepository.findAll();

        return sectionEntityList;

    }
}