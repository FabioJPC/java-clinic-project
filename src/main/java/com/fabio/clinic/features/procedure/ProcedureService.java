package com.fabio.clinic.features.procedure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProcedureService {

    private final ProcedureRepository repository;

    public ProcedureService(ProcedureRepository procedureRepository){
        this.repository = procedureRepository;
    }

    public List<Procedure> findAll(){
        return repository.findAll();
    }
    public Procedure create(ProcedureRequestDTO data){
        Procedure procedure = new Procedure(data);
        return repository.save(procedure);
    }
}
