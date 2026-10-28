package com.example.demo.presentation.controller;

import com.example.demo.application.service.ServicioReporte;
import com.example.demo.domain.models.Reporte;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/reportes")
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:3001",
        "http://localhost:5173",
        "http://127.0.0.1:3000",
        "http://127.0.0.1:5173"
})
@Validated
public class ControladorReporte {

    @Autowired
    ServicioReporte servicio;

    @PostMapping
    public ResponseEntity<Reporte> guardar(@Valid @RequestBody Reporte datos) {
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.ok("Reporte eliminado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Reporte datos) {
        datos.setId(id);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }
}
