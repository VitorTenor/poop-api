package com.vitortenorio.poop_api.api.v1.assembler;

import com.vitortenorio.poop_api.api.v1.output.ShitterOutput;
import com.vitortenorio.poop_api.entity.ShitterEntity;
import org.springframework.stereotype.Component;

@Component
public class ShitterAssembler implements Assembler<ShitterEntity, ShitterOutput> {
    @Override
    public ShitterOutput toOutput(ShitterEntity entity) {
        return ShitterOutput.builder().name(entity.name()).build();
    }
}
