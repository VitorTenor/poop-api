package com.vitortenorio.poop_api.api.v1.model.mapper;

import lombok.Data;

@Data
public class ShitterRankingMapper {
    private String name;
    private Long position;
    private Long points;
    private String imageBase64;

    public ShitterRankingMapper(String name, Long position, String imageBase64, Long points) {
        this.name = name;
        this.position = position;
        this.imageBase64 = imageBase64;
        this.points = points;
    }
}
