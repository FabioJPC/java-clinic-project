package com.fabio.clinic.features.procedure;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProcedureRequestDTO(
        @NotNull String name,
        @NotNull Integer durationInMinutes,
        @NotNull BigDecimal price
        ){
}
