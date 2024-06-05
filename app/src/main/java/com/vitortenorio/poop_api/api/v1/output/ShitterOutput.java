package com.vitortenorio.poop_api.api.v1.output;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShitterOutput {
    private final String name;
}
