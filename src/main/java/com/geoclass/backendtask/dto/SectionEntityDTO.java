package com.geoclass.backendtask.dto;


import com.geoclass.backendtask.entities.GeologicalClassEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class SectionEntityDTO {


    @Setter
    private int sectionId;
    @Setter
    private String sectionName;
    @Setter
    private Set<GeologicalClassEntity> geologicalClassEntitySet = new HashSet<>();
}
