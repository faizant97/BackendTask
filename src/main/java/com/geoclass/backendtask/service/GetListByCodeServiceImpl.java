package com.geoclass.backendtask.service;


import com.geoclass.backendtask.entities.GeologicalClassEntity;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.repositories.GeologicalClassRepository;
import com.geoclass.backendtask.repositories.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetListByCodeServiceImpl implements GetListByCodeService{


    private final SectionRepository sectionRepository;


    public GetListByCodeServiceImpl(SectionRepository sectionRepository) {

        this.sectionRepository =sectionRepository;
    }

    @Override
    public List<String> getSectionListByClassCode(String code){
        List<String> sectionList = new ArrayList<>();
        List<SectionEntity> tempList = sectionRepository.findAll();
        for(SectionEntity section: tempList){
            for (GeologicalClassEntity geoClass: section.getGeologicalClass()) {
                if (geoClass.getClassCode().equals(code)) {
                    sectionList.add(section.getSectionName());
                    break;
                }
            }
        }
        return sectionList;

    }

}
