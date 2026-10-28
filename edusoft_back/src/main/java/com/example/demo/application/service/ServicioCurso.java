package com.example.demo.application.service;

import com.example.demo.domain.models.Curso;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioProfesor;
import com.example.demo.infrastructure.repository.IRepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServicioCurso {

    // el servicio utiliza al repositorio, a este uso se le llama inyeccion de dependencia

    @Autowired
    IRepositorioCurso repositorio;

    @Autowired
    IRepositorioProfesor repositorioProfesor;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer

    public Curso guardar(Curso datos){
        Integer idProfesor = datos.getIdProfesor();
        if (idProfesor != null && !this.repositorioProfesor.existsById(idProfesor)) {
            throw new ResourceNotFoundException("No se encontró el profesor con ID " + idProfesor);
        }
        return this.repositorio.save(datos);
    }

    public List<Curso> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Curso buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el curso con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el curso con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}