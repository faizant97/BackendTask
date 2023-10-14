package com.geoclass.backendtask.controller;

import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.service.SectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping ("/getAllSections")
    public List<SectionEntity> getListOfSection(){
        List<SectionEntity> sectionEntityList;
        sectionEntityList = sectionService.getSection();
        return sectionEntityList;
    }




}
