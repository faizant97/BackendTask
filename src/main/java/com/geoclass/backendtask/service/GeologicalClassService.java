package com.geoclass.backendtask.service;

import com.geoclass.backendtask.dto.CreateGeologicalClassDTO;
import com.geoclass.backendtask.dto.UpdateGeologicalClassDTO;
import com.geoclass.backendtask.entities.GeologicalClassEntity;

import java.util.List;

public interface GeologicalClassService {
    List<GeologicalClassEntity> getGeologicalClass();
    GeologicalClassEntity createGeologicalClass(CreateGeologicalClassDTO createGeologicalClassDTO);
    String deleteGeologicalClass(CreateGeologicalClassDTO createGeologicalClassDTO);
    String updateGeologicalClass(UpdateGeologicalClassDTO updateGeologicalClassDTO);

   // String assignSectionToGeologicalClass(int sectionEntity, int geologicalClassEntity);
}
