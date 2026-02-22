package com.fabio.clinic.features.patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@SoftDelete(columnName = "active")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    private String name;
    private String phone;
    private LocalDate birthdate;

    public Patient(PatientRequestDTO data){
        this.email = data.email();
        this.name = data.name();
        this.phone = data.phone();
        this.birthdate = data.birthdate();
        this.cpf = data.cpf();
    }
}

