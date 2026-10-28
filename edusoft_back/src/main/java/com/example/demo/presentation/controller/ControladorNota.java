package com.example.demo.presentation.controller;

import com.example.demo.domain.models.Nota;
import com.example.demo.application.service.ServicioNota;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/notas")
@CrossOrigin("http://localhost:3000")
@Validated
public class ControladorNota {

    @Autowired
    ServicioNota servicio;

    @PostMapping
    public ResponseEntity<Nota> guardar(@Valid @RequestBody Nota datos) {
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> buscar(@RequestParam(required = false) String email) {
        if (email != null && !email.isBlank()) {
            return ResponseEntity.ok(this.servicio.buscarPorEmail(email));
        }
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.ok("Nota eliminada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Nota datos) {
        datos.setId(id);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }
}
