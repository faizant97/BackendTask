package com.geoclass.backendtask.controller;

import com.geoclass.backendtask.service.ExportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/start")
    public CompletableFuture<Long> startExport() throws IOException {
        return exportService.exportXLSFile();
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> checkExportStatus(@PathVariable Long id){
        return exportService.returnStatusOfJob(id);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException {
      return  exportService.downloadFile(id);
    }
}
