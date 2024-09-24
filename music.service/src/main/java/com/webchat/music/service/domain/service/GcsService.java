package com.webchat.music.service.domain.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.webchat.music.service.domain.service.Inter.ICloudService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GcsService implements ICloudService {

    private final Storage storage;

    @Value("${gcp.bucket-name}")
    private String bucketName;

    public GcsService(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try (InputStream inputStream = file.getInputStream()) {
            storage.create(blobInfo, inputStream.readAllBytes());
        }
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    @Override
    public byte[] downloadFile(String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        Blob blob = storage.get(blobId);
        if (blob == null || !blob.exists()) {
            throw new IOException("File not found: " + fileName);
        }
        return blob.getContent();
    }

    @Override
    public String getContentType(String fileName) {
        BlobId blobId = BlobId.of(bucketName, fileName);
        Blob blob = storage.get(blobId);
        if (blob == null || !blob.exists()) {
            return "application/octet-stream"; // default binary type if not found
        }
        return blob.getContentType();
    }
}
