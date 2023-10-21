package com.geoclass.backendtask.controller;


import com.geoclass.backendtask.service.ImportService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@EnableWebSecurity
@RequestMapping("/import")
public class ImportController {
    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/upload")
    public CompletableFuture<Long> uploadFile(@RequestParam ("file")MultipartFile file){
        return importService.startImport(file);
    }

    @GetMapping("/{id}")
    public String getImportJobStatus(@PathVariable Long id){
        return importService.getImportStatus(id);
    }

}
