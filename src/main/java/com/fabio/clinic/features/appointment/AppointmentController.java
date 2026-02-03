package com.fabio.clinic.features.appointment;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService service){
        this.appointmentService = service;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@Valid @RequestBody AppointmentRequestDTO requestDTO){
        var procedure = appointmentService.schedule(requestDTO);
        return ResponseEntity.ok(procedure);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> listAll(){
        return ResponseEntity.ok(appointmentService.findAll());
    }
}
