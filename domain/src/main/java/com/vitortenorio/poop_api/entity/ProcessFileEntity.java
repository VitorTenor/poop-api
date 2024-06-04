package com.vitortenorio.poop_api.entity;

import java.time.LocalDate;

public record ProcessFileEntity (
        LocalDate startDate,
        byte[] file
) {
}
