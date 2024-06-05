package com.vitortenorio.poop_api.gateway;

import com.vitortenorio.poop_api.entity.ShitterEntity;
import com.vitortenorio.poop_api.entity.ShitterRankingEntity;
import com.vitortenorio.poop_api.entity.UploadShitterImageEntity;

import java.util.List;

public interface ShitterGateway {
    void uploadImage(UploadShitterImageEntity entity);
    List<ShitterRankingEntity> ranking();
    List<ShitterEntity> shitters();
    ShitterEntity shitter(String name);
}
