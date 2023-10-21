package com.geoclass.backendtask.dto;

import lombok.Getter;

@Getter
public enum StatusEnum {
    DONE("DONE"),
    IN_PROGRESS("IN_PROGRESS"),
    ERROR("ERROR");

    private final String displayStatus;

    StatusEnum(String displayStatus) {
        this.displayStatus = displayStatus;
    }

}
