package com.example.fadeshare.service;

import java.io.InputStream;

public interface StorageService {

    void upload(String objectName, InputStream data, long size, String contentType);
    InputStream download(String objectName);
    void delete(String objectName);
}