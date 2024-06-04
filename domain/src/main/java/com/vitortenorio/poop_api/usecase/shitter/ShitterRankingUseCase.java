package com.vitortenorio.poop_api.usecase.shitter;

import com.vitortenorio.poop_api.entity.ShitterRankingEntity;
import com.vitortenorio.poop_api.gateway.ShitterGateway;

import javax.inject.Named;
import java.util.List;

@Named
public class ShitterRankingUseCase {
    private final ShitterGateway shitterGateway;

    public ShitterRankingUseCase(ShitterGateway shitterGateway) {
        this.shitterGateway = shitterGateway;
    }

    public List<ShitterRankingEntity> execute() {
        return shitterGateway.ranking();
    }
}
