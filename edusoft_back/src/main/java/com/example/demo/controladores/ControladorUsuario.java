package com.example.demo.controladores;

import com.example.demo.modelos.Usuario;
import com.example.demo.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisura8/v1/usuarios")
@CrossOrigin("http://localhost:3000")
public class ControladorUsuario {

    //Inyectemos la dependencia
    @Autowired
    ServicioUsuario servicio;

    //Llamamos a cada servicio
    //Una peticion tiene dos partes Cabecera y cuerpo
    @PostMapping
    public ResponseEntity<?> ControladorGuardar(@RequestBody Usuario datos){

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardar(datos));
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }



    }
    @GetMapping
    public ResponseEntity<?> ControladorBuscarTodos(){

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.BuscarTodos());
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> ControladorBusarPotId(@PathVariable Integer id){

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.BuscarPorId(id));
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }

    }

}

