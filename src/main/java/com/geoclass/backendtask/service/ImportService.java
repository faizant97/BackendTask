package com.geoclass.backendtask.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface ImportService {
//    Long importFileAsync(MultipartFile file);
//    String getImportStatus(Long id);

    CompletableFuture<Long> startImport(MultipartFile file);
}
