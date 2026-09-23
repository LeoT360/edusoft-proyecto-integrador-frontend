package com.example.demo.controladores;

import com.example.demo.modelos.Curso;
import com.example.demo.servicios.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisura8/v1/cursos")
@CrossOrigin("http://localhost:3000")
public class ControladorCurso {

    @Autowired
    ServicioCurso servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Curso datos) {
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

    @GetMapping
    public ResponseEntity<?> controladorBuscarTodos() {
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

    // ✅ BUG CORREGIDO: era @GetMapping("{/id}") — la barra estaba dentro
    //    de las llaves, lo que generaba una ruta literal "{/id}" en lugar
    //    de capturar el parámetro. Correcto: "/{id}"
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
