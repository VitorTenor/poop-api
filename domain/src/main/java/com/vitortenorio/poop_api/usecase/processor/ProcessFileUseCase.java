package com.vitortenorio.poop_api.usecase.processor;

import com.vitortenorio.poop_api.entity.ProcessFileEntity;
import com.vitortenorio.poop_api.gateway.ProcessorGateway;

import javax.inject.Named;

@Named
public class ProcessFileUseCase {
    private final ProcessorGateway processorGateway;

    public ProcessFileUseCase(ProcessorGateway processorGateway) {
        this.processorGateway = processorGateway;
    }

    public void execute(ProcessFileEntity entity){
        processorGateway.processFile(entity);
    }
}
