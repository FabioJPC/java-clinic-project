package com.fabio.clinic.features.procedure;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    private final ProcedureRepository repository;
    private final ProcedureMapper mapper;

    public ProcedureService(ProcedureRepository procedureRepository, ProcedureMapper procedureMapper){

        this.repository = procedureRepository;
        this.mapper = procedureMapper;
    }

    @Transactional(readOnly = true)
    public List<ProcedureResponseDTO> findAll(){

        return repository.findAll()
                .stream()
                .map(ProcedureResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProcedureResponseDTO show(Long id){

        return new ProcedureResponseDTO(repository.findById(id).orElseThrow());
    }

    public ProcedureResponseDTO create(ProcedureRequestDTO data){
        Procedure procedure = new Procedure(data);
        return new ProcedureResponseDTO(repository.save(procedure));
    }

    @Transactional
    public ProcedureResponseDTO update(Long id, ProcedureUpdateDTO data){
        Procedure procedure = repository.findById(id).orElseThrow();
        mapper.UpdateProcedureFromDTO(data, procedure);
        return new ProcedureResponseDTO(repository.save(procedure));
    }

    public void delete(Long id){
        Procedure procedure = repository.findById(id).orElseThrow();
        repository.delete(procedure);
    }
}
