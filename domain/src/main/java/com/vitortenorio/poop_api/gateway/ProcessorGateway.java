package com.vitortenorio.poop_api.gateway;

import com.vitortenorio.poop_api.entity.ProcessFileEntity;

public interface ProcessorGateway {
    void processFile(ProcessFileEntity entity);
}
