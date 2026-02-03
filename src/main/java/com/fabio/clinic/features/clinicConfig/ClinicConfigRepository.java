package com.fabio.clinic.features.clinicConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface ClinicConfigRepository extends JpaRepository<ClinicConfig, Long> {

    Optional<ClinicConfig> findByDayOfWeek(DayOfWeek day);
}
