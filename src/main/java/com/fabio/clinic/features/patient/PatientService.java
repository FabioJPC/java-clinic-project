package com.fabio.clinic.features.patient;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper){
        this.repository = patientRepository;
        this.mapper = patientMapper;
    }

    public List<PatientResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(PatientResponseDTO::new)
                .toList();
    }

    @Transactional
    public PatientResponseDTO create(PatientRequestDTO data){
        if (repository.existsByEmail(data.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado!");
        }
        if (repository.existsByCpf(data.cpf())){
            throw new RuntimeException("Este CPF já está cadastrado!");
        }
        Patient patient = new Patient(data);
        return new PatientResponseDTO(repository.save(patient));
    }

    @Transactional
    public PatientResponseDTO update(Long id, PatientUpdateDTO data){
        Patient patient = repository.findById(id).orElseThrow();
        if(data.cpf() != null && !data.cpf().equals(patient.getCpf())){
            if(repository.existsByCpf(data.cpf())){
                throw new RuntimeException("Este CPF já está sendo usado por outro paciente");
            }
        }
        mapper.UpdatePatientFromDto(data, patient);
        return new PatientResponseDTO(repository.save(patient));
    }

    public void delete(Long id){
        Patient patient = repository.findById(id).orElseThrow(()->new RuntimeException("Paciente não encontrado."));
        repository.delete(patient);
    }
}
