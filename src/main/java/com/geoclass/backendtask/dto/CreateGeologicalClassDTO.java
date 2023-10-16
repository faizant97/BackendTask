package com.geoclass.backendtask.dto;


import lombok.Data;

@Data
public class CreateGeologicalClassDTO {
    private String className;
    private String classCode;
    private SectionEntityDTO section;
}
