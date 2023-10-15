package com.geoclass.backendtask.dto;

import lombok.Data;

@Data
public class UpdateGeologicalClassDTO {
    private String existingName;
    private String existingClassCode;
    private String updatedName;
    private String updatedClassCode;

}
