package com.example.fadeshare.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileService {

    private final StorageService storageService;

    public FileService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void uploadFile(MultipartFile file){
        try {
            String objectName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            storageService.upload(
                    objectName,
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error Uploading File");
        }
    }
}
