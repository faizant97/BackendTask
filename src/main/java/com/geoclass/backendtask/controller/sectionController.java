package com.geoclass.backendtask.controller;

import com.geoclass.backendtask.entities.sectionEntity;
import com.geoclass.backendtask.service.sectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class sectionController {
    private final sectionService sectionService;

    public sectionController(sectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping ("/getAllSections")
    public List<sectionEntity> getListOfSection(){
        List<sectionEntity> sectionEntityList;
        sectionEntityList = sectionService.getSection();
        return sectionEntityList;
    }




}
