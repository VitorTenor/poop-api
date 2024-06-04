package com.vitortenorio.poop_api.usecase.shitter;

import com.vitortenorio.poop_api.entity.UploadShitterImageEntity;
import com.vitortenorio.poop_api.gateway.ShitterGateway;

import javax.inject.Named;

@Named
public class UploadShitterImageUseCase {
    private final ShitterGateway shitterGateway;

    public UploadShitterImageUseCase(ShitterGateway shitterGateway) {
        this.shitterGateway = shitterGateway;
    }

    public void execute(UploadShitterImageEntity entity) {
        shitterGateway.uploadImage(entity);
    }
}
