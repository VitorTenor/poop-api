package com.vitortenorio.poop_api.entity;

public record ShitterRankingEntity(
        String name,
        Long position,
        Long points,
        String imageBase64
) {
}
