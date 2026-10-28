package com.example.demo.application.service;

import com.example.demo.domain.models.Profesor;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProfesor {

    //El servicio utiliza al repositorio
    // a este uso se le llama inyeccion de dependencia

    //inyectar
    @Autowired
    IRepositorioProfesor repositorio;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer
    //3 funciones
    public Profesor guardar(Profesor datos){
        return this.repositorio.save(datos);
    }

    public List<Profesor> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Profesor buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el profesor con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el profesor con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}
