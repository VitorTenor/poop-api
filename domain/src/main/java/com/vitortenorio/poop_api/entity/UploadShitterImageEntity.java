package com.vitortenorio.poop_api.entity;

public record UploadShitterImageEntity(
        String shitterName,
        byte[] image
) {
}
