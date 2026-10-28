package com.example.demo.presentation.controller;

import com.example.demo.domain.models.Asistencia;
import com.example.demo.application.service.ServicioAsistencia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/asistencias")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ControladorAsistencia {

    private final ServicioAsistencia service;

    public ControladorAsistencia(ServicioAsistencia service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Asistencia> guardar(@Valid @RequestBody Asistencia asistencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(asistencia));
    }

    @GetMapping
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Asistencia eliminada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Asistencia datos) {
        datos.setId(id);
        return ResponseEntity.ok(service.crear(datos));
    }
}
