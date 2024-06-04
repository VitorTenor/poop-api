package com.vitortenorio.poop_api.api.v1.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileResultDTO {
    private String hour;
    private String name;
}
