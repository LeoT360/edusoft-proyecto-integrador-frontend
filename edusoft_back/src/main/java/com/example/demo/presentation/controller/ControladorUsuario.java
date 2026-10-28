package com.example.demo.presentation.controller;

import com.example.demo.domain.models.Usuario;
import com.example.demo.application.service.ServicioUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiedusoft/v1/usuarios")
@CrossOrigin("http://localhost:3000")
@Validated
public class ControladorUsuario {

    @Autowired
    ServicioUsuario servicio;

    @PostMapping
    public ResponseEntity<Usuario> guardar(@Valid @RequestBody Usuario datos) {
        datos.setId(null);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }

    @GetMapping
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @Positive Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable @Positive Integer id,
            @Valid @RequestBody Usuario datos) {
        datos.setId(id);
        return ResponseEntity.ok(this.servicio.guardar(datos));
    }
}
