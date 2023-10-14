package com.geoclass.backendtask.controller;


import com.geoclass.backendtask.dto.CreateGeologicalClassDTO;
import com.geoclass.backendtask.dto.UpdateGeologicalClassDTO;
import com.geoclass.backendtask.entities.GeologicalClassEntity;
import com.geoclass.backendtask.service.GeologicalClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geologicalClass")
public class GeologicalClassController {
    private final GeologicalClassService geologicalClassService;
    public GeologicalClassController(GeologicalClassService geologicalClassService) {
        this.geologicalClassService = geologicalClassService;
    }

    @GetMapping("/getAllGeologicalClasses")
    public List<GeologicalClassEntity> getGeologicalClasses(){
        List<GeologicalClassEntity> geologicalClassEntityList;
        geologicalClassEntityList = geologicalClassService.getGeologicalClass();
        return geologicalClassEntityList;
    }

    @PostMapping(value = "/createGeologicalClass", consumes = "application/json", produces = "application/json")
    public GeologicalClassEntity createGeologicalClass(@RequestBody CreateGeologicalClassDTO createGeologicalClassDTO){
        return geologicalClassService.createGeologicalClass(createGeologicalClassDTO);
    }

    @PostMapping(value = "/deleteGeologicalClass", consumes = "application/json", produces = "application/json")
    public String deleteGeologicalClass(@RequestBody CreateGeologicalClassDTO createGeologicalClassDTO){
        return geologicalClassService.deleteGeologicalClass(createGeologicalClassDTO);
    }

    @PostMapping(value = "/updateGeologicalClass", consumes = "application/json", produces = "application/json")
    public String updateGeologicalClass(@RequestBody UpdateGeologicalClassDTO updateGeologicalClassDTO){
        return geologicalClassService.updateGeologicalClass(updateGeologicalClassDTO);
    }
}
