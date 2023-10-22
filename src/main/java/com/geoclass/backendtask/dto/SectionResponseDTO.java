package com.geoclass.backendtask.dto;

import com.geoclass.backendtask.entities.GeologicalClassEntity;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SectionResponseDTO {

        private String sectionName;

        private List<GeologicalClassEntity> geologicalClassEntitySet = new ArrayList<>();

}
