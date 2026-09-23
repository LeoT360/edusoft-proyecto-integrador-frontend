package com.example.demo.controladores;

import com.example.demo.modelos.Asistencia;
import com.example.demo.servicios.ServicioAsistencia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apisura8/v1/asistencias")
@CrossOrigin(origins = "http://localhost:3000")
public class ControladorAsistencia {

    private final ServicioAsistencia service;

    public ControladorAsistencia(ServicioAsistencia service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Asistencia asistencia) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(asistencia));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> listar() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
