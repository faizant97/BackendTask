package com.geoclass.backendtask.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface ExportService  {
//    Long exportFileAsync();
//    String getExportStatus(Long id);
//    ResponseEntity<byte[]> getExportFile(Long id);
    CompletableFuture<Long> exportXLSFile() throws IOException;
    ResponseEntity<String> returnStatusOfJob(Long jobId);
    ResponseEntity<byte[]> downloadFile(Long jobId) throws IOException;
}
