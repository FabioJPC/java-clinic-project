package com.fabio.clinic.features.procedure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer durationInMinutes;
    private BigDecimal price;

    public Procedure(ProcedureRequestDTO data){
        this.name = data.name();
        this.price = data.price();
        this.durationInMinutes = data.durationInMinutes();
    }

}
