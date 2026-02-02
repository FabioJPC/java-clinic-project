package com.fabio.clinic.features.procedure;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {
    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService service){
        this.procedureService = service;
    }

    @GetMapping
    ResponseEntity<List<Procedure>> listAll(){
        return ResponseEntity.ok(procedureService.findAll());
    }

    @PostMapping
    ResponseEntity<Procedure> create(@Valid @RequestBody ProcedureRequestDTO requestDto){
        var procedure = procedureService.create(requestDto);
        return ResponseEntity.ok(procedure);
    }
}
