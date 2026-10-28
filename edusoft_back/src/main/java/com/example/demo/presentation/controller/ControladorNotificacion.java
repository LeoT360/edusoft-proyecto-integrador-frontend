package com.example.demo.presentation.controller;

import com.example.demo.domain.models.Notificacion;
import com.example.demo.application.service.ServicioNotificacion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/notificaciones")
@CrossOrigin("http://localhost:3000")
@Validated
public class ControladorNotificacion {

    @Autowired
    private ServicioNotificacion servicio;

    @PostMapping
    public ResponseEntity<Notificacion> guardar(@Valid @RequestBody Notificacion datos) {
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.ok("Notificación eliminada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Notificacion datos) {
        datos.setId(id);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }
}
