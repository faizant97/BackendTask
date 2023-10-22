package com.geoclass.backendtask.controller;

import com.geoclass.backendtask.dto.CreateSectionDTO;
import com.geoclass.backendtask.dto.DeleteSectionDTO;
import com.geoclass.backendtask.dto.SectionResponseDTO;
import com.geoclass.backendtask.dto.UpdateSectionDTO;
import com.geoclass.backendtask.entities.SectionEntity;
import com.geoclass.backendtask.service.SectionService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@EnableWebSecurity
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping ("/getAllSections")
    public List<SectionEntity> getListOfSection(){

        return sectionService.getSection();
    }


    @PostMapping(value = "/createSection")
    public SectionResponseDTO createSection(@RequestBody CreateSectionDTO sectionDTO){
        return sectionService.createSection(sectionDTO);
    }


    @PostMapping(value = "/deleteSection", consumes = "application/json", produces = "application/json")
    public DeleteSectionDTO deleteSection(@RequestBody CreateSectionDTO sectionDTO){
        return sectionService.deleteSection(sectionDTO);
    }

    @PostMapping(value = "/updateSection", consumes = "application/json", produces = "application/json")
    public String updateSection(@RequestBody UpdateSectionDTO updateSectionDTO){
        return sectionService.updateSection(updateSectionDTO);
    }

}
