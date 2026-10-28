package com.example.demo.presentation.controller;

import com.example.demo.domain.models.Matricula;
import com.example.demo.application.service.ServicioMatricula;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/matriculas")
@CrossOrigin("http://localhost:3000")
@Validated
public class ControladorMatricula {

    @Autowired
    ServicioMatricula servicio;

    @PostMapping
    public ResponseEntity<Matricula> guardar(@Valid @RequestBody Matricula datos) {
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.ok("Matrícula eliminada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Matricula datos) {
        datos.setId(id);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }
}
