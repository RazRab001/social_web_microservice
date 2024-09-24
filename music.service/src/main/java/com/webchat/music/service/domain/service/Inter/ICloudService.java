package com.webchat.music.service.domain.service.Inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudService {
    String uploadFile(MultipartFile file) throws IOException;
    byte[] downloadFile(String fileName) throws IOException;
    String getContentType(String fileName);
}
