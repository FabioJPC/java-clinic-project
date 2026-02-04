package com.fabio.clinic.features.procedure;

import java.math.BigDecimal;

public record ProcedureResponseDTO(
         String name,
         Integer durationInMinutes,
         BigDecimal price
) {
    public ProcedureResponseDTO(Procedure procedure){
        this(procedure.getName(), procedure.getDurationInMinutes(), procedure.getPrice());
    }
}
