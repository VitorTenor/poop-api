package com.vitortenorio.poop_api.api.v1.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShitterRankingOutput {
    private final String name;
    private final Long position;
    private final Long points;
    private final String imageBase64;
}
