package com.webchat.music.service.domain.service;

import com.mpatric.mp3agic.Mp3File;
import com.webchat.music.service.domain.service.Inter.IAudioService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AudioService implements IAudioService {
    public long getDuration(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("temp", ".mp3");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        try {
            Mp3File mp3File = new Mp3File(tempFile.getAbsolutePath());
            return mp3File.getLengthInSeconds();
        } catch (Exception e) {
            throw new IOException("Error reading audio file", e);
        } finally {
            tempFile.delete();
        }
    }
}