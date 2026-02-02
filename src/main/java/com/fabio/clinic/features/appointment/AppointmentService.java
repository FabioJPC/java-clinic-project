package com.fabio.clinic.features.appointment;

import com.fabio.clinic.features.doctor.DoctorRepository;
import com.fabio.clinic.features.patient.PatientRepository;
import com.fabio.clinic.features.procedure.ProcedureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            ProcedureRepository procedureRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;

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

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

}
