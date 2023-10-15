package com.geoclass.backendtask.dto;


import com.geoclass.backendtask.entities.GeologicalClassEntity;
import lombok.Data;

import java.util.List;


@Data
public class CreateSectionDTO {

   private String name;
   private List<GeologicalClassEntity> geologicalClassEntityList;
}
