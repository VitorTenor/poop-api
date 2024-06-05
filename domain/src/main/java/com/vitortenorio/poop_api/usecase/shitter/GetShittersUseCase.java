package com.vitortenorio.poop_api.usecase.shitter;

import com.vitortenorio.poop_api.entity.ShitterEntity;
import com.vitortenorio.poop_api.gateway.ShitterGateway;

import javax.inject.Named;
import java.util.List;

@Named
public class GetShittersUseCase {
    private final ShitterGateway shitterGateway;

    public GetShittersUseCase(ShitterGateway shitterGateway) {
        this.shitterGateway = shitterGateway;
    }

    public List<ShitterEntity> execute() {
        return shitterGateway.shitters();
    }
}
