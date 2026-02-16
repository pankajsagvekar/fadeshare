package com.example.fadeshare.service.impl;

import com.example.fadeshare.service.StorageService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MinioStorageService implements StorageService {

    private final MinioClient minioClient;
    private final String bucketName;

    public MinioStorageService(MinioClient minioClient, @Value("${minio.bucket}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    @Override
    public void upload(String objectName, InputStream data, long size, String contentType) {
        try {
            minioClient.putObject(
                    io.minio.PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(data, size, -1)
                            .contentType(contentType)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Error Uploading File");
        }
    }

    @Override
    public InputStream download(String objectName) {
        try {
            return minioClient.getObject(
                    io.minio.GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Error Downloading File");
        }
    }

    @Override
    public void delete(String objectName) {
        try {
            minioClient.removeObject(
                    io.minio.RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Error Deleting File");
        }
    }
}
