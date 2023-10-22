package com.geoclass.backendtask.dto;

import com.geoclass.backendtask.entities.GeologicalClassEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class DeleteSectionDTO {



    @Setter
    private String sectionName;
    @Setter
    List<GeologicalClassEntity> geologicalClass = new ArrayList<>();
    @Setter
    private  String message;
}
