package com.fabio.clinic.features.procedure;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/procedures")
public class ProcedureController {
    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService service){
        this.procedureService = service;
    }

    @GetMapping
    ResponseEntity<List<ProcedureResponseDTO>> listAll(){
        return ResponseEntity.ok(procedureService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProcedureResponseDTO> show(@PathVariable Long id){
        return ResponseEntity.ok(procedureService.show(id));
    }

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping
    ResponseEntity<ProcedureResponseDTO> create(@Valid @RequestBody ProcedureRequestDTO requestDto){
        var procedure = procedureService.create(requestDto);
        return ResponseEntity.ok(procedure);
    }

    @PreAuthorize("hasRole('STAFF')")
    @PutMapping("/{id}")
    ResponseEntity<ProcedureResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProcedureUpdateDTO dto){
        return ResponseEntity.ok(procedureService.update(id, dto));
    }

    @PreAuthorize("hasRole('STAFF')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        procedureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
