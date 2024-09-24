package com.webchat.music.service.domain.service.Inter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAudioService {
    long getDuration(MultipartFile file) throws IOException;
}
