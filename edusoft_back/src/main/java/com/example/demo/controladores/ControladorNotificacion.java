package com.example.demo.controladores;

import com.example.demo.modelos.Notificacion;
import com.example.demo.servicios.ServicioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisura8/v1/notificaciones")
@CrossOrigin("http://localhost:3000")
public class ControladorNotificacion {
    // ===== INYECCIÓN DE DEPENDENCIAS =====
    @Autowired
    private ServicioNotificacion servicio;
    // ===== GUARDAR NOTIFICACIÓN =====
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Notificacion datos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardar(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    // ===== BUSCAR TODAS LAS NOTIFICACIONES =====
    @GetMapping
    public ResponseEntity<?>controladorBuscarTodos() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarTodos());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    // ===== BUSCAR NOTIFICACIÓN POR ID =====
    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}