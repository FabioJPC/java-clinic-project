package com.fabio.clinic.features.procedure;

import java.math.BigDecimal;

public record ProcedureUpdateDTO (
         String name,
         Integer durationInMinutes,
         BigDecimal price
){
}
