package com.vitortenorio.poop_api.api.v1.controller;

import com.vitortenorio.poop_api.api.v1.input.ProcessFileInput;
import com.vitortenorio.poop_api.usecase.processor.ProcessFileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/process")
public class ProcessController {
    private final ProcessFileUseCase processFileUseCase;

    @PostMapping
    public void process(@ModelAttribute ProcessFileInput input){
        processFileUseCase.execute(input.toEntity());
    }
}
