package com.vitortenorio.poop_api.api.v1.assembler;

import com.vitortenorio.poop_api.api.v1.output.ShitterRankingOutput;
import com.vitortenorio.poop_api.entity.ShitterRankingEntity;
import org.springframework.stereotype.Component;

@Component
public class ShitterRankingAssembler implements Assembler<ShitterRankingEntity, ShitterRankingOutput>{
    @Override
    public ShitterRankingOutput toOutput(ShitterRankingEntity entity) {
        return ShitterRankingOutput.builder()
                .name(entity.name())
                .position(entity.position())
                .points(entity.points())
                .imageBase64(entity.imageBase64())
                .build();
    }
}
