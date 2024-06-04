package com.vitortenorio.poop_api.api.v1.input;

import com.vitortenorio.poop_api.entity.ProcessFileEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public record ProcessFileInput(
        MultipartFile file,
        @DateTimeFormat(pattern = "dd/MM/yyyy" )
        LocalDate startDate
) {
    public ProcessFileEntity toEntity() {
        try {
            return new ProcessFileEntity(startDate, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error converting file to byte array", e);
        }
    }
}
