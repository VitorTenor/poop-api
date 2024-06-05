package com.vitortenorio.poop_api.usecase.shitter;

import com.vitortenorio.poop_api.entity.ShitterEntity;
import com.vitortenorio.poop_api.gateway.ShitterGateway;

import javax.inject.Named;

@Named
public class GetShitterUseCase {
    private final ShitterGateway shitterGateway;

    public GetShitterUseCase(ShitterGateway shitterGateway) {
        this.shitterGateway = shitterGateway;
    }

    public ShitterEntity execute(final String name) {
        return shitterGateway.shitter(name);
    }
}
