package com.vitortenorio.poop_api.api.v1.controller;

import com.vitortenorio.poop_api.api.v1.assembler.ShitterRankingAssembler;
import com.vitortenorio.poop_api.api.v1.input.ShitterImageUploadInput;
import com.vitortenorio.poop_api.api.v1.output.ShitterRankingOutput;
import com.vitortenorio.poop_api.usecase.shitter.ShitterRankingUseCase;
import com.vitortenorio.poop_api.usecase.shitter.UploadShitterImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shitter")
public class ShitterController {
    private final UploadShitterImageUseCase uploadShitterImageUseCase;
    private final ShitterRankingUseCase shitterRankingUseCase;

    private final ShitterRankingAssembler shitterRankingAssembler;

    @GetMapping("/ranking")
    public List<ShitterRankingOutput> ranking() {
        return shitterRankingAssembler.toOutputList(
                shitterRankingUseCase.execute()
        );
    }

    @PostMapping("/image/upload")
    public void uploadImage(@ModelAttribute ShitterImageUploadInput input) throws IOException {
        uploadShitterImageUseCase.execute(input.toEntity());
    }
}
