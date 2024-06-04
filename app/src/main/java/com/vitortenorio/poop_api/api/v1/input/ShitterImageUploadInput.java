package com.vitortenorio.poop_api.api.v1.input;

import com.vitortenorio.poop_api.entity.UploadShitterImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public record ShitterImageUploadInput(
        String shitterName,
        MultipartFile image
) {

    public UploadShitterImageEntity toEntity() throws IOException {
        return new UploadShitterImageEntity(shitterName, image.getBytes());
    }
}
