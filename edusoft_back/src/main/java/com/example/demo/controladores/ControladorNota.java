package com.example.demo.controladores;


import com.example.demo.modelos.Nota;
import com.example.demo.servicios.ServicioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisura8/v1/notas")
@CrossOrigin("http://localhost:3000")
public class ControladorNota {
    @Autowired
    ServicioNota servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Nota datos){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardar(datos));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscar(
            @RequestParam(required = false) String email){

        try {

            if (email != null && !email.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(this.servicio.buscarPorEmail(email));
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarTodos());

        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarPorId(id));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try{
            this.servicio.eliminar(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Nota eliminada correctamente");
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id,
            @RequestBody Nota datos) {
        try {
            datos.setId(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardar(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
