package com.fabio.clinic.features.appointment;

import com.fabio.clinic.features.clinicConfig.ClinicConfig;
import com.fabio.clinic.features.clinicConfig.ClinicConfigRepository;
import com.fabio.clinic.features.doctor.DoctorRepository;
import com.fabio.clinic.features.patient.PatientRepository;
import com.fabio.clinic.features.procedure.ProcedureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;
    private final ClinicConfigRepository clinicConfigRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            ProcedureRepository procedureRepository,
            ClinicConfigRepository clinicConfigRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;
        this.clinicConfigRepository = clinicConfigRepository;

    }

    @Transactional
    public AppointmentResponseDTO schedule(AppointmentRequestDTO data){

        var procedure = procedureRepository.findById(data.procedure_id()).
                orElseThrow(()->new RuntimeException("O tipo de procedimento não foi encontrado."));
        var doctor = doctorRepository.findById(data.doctor_id()).
                orElseThrow(() ->new RuntimeException("Doutor não encontrado"));
        var patient = patientRepository.findById(data.patient_id()).
                orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        LocalDateTime start = data.dateTime();
        LocalDateTime end = start.plusMinutes(procedure.getDurationInMinutes());

        //validate if the appointment is within operational dates and time.
        validateClinicSchedule(start);

        //check date and time concurrency
        boolean hasOverlap = appointmentRepository.existsOverlapping(data.doctor_id(), start, end);

        if(hasOverlap){
            throw new RuntimeException("O médico está ocupado nesse período");
        }

        var appointment = new Appointment();
        appointment.setProcedure(procedure);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateTime(data.dateTime());
        appointment.setEndDateTime(end);
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        var response = appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(response);
    }

    public List<AppointmentResponseDTO> findAll(){

        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::new)
                .toList();
    }

    public void validateClinicSchedule(LocalDateTime appointmentStart){
        DayOfWeek dayOfWeek = appointmentStart.getDayOfWeek();
        String dayPt = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.of("pt", "br"));
        LocalTime localTime = appointmentStart.toLocalTime();

        ClinicConfig config = clinicConfigRepository.findByDayOfWeek(dayOfWeek).
                orElseThrow(()->new RuntimeException("Configuração não encontrada"));


        //validate if clinic is open
        if(!config.getIsOpen()){
            throw new RuntimeException("A clínica não abre aos " + dayPt);
        }

        //validate if appointment is between working hours
        if (localTime.isBefore(config.getOpenTime()) || localTime.isAfter(config.getCloseTime())) {
            throw new RuntimeException("Horário fora do expediente da clínica.");
        }

        //validate if is not lunchtime
        if(config.getBreakStart() != null && config.getBreakEnd() != null){
            if (localTime.isAfter(config.getBreakStart()) && localTime.isBefore(config.getBreakEnd())) {
                throw new RuntimeException("A clínica está em horário de intervalo.");
            }
        }
    }

}
