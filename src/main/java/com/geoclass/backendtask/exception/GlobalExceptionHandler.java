package com.geoclass.backendtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExportInProgressException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map<String, String> handleExportInProgressException(ExportInProgressException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(ExportErrorException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map<String, String> handleExportErrorException(ExportErrorException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }
}