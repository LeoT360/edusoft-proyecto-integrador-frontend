package com.example.demo.application.service;

import com.example.demo.domain.models.Matricula;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioCurso;
import com.example.demo.infrastructure.repository.IRepositorioMatricula;
import com.example.demo.infrastructure.repository.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMatricula {

    //el servicio utiliza al repositorio
    //a este uso de se le llama inyeccion de dependencia

    @Autowired
    IRepositorioMatricula repositorio;

    @Autowired
    IRepositorioUsuario repositorioUsuario;

    @Autowired
    IRepositorioCurso repositorioCurso;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer
    public Matricula guardar(Matricula datos){
        Integer idUsuario = datos.getIdUsuario();
        if (idUsuario != null && !this.repositorioUsuario.existsById(idUsuario)) {
            throw new ResourceNotFoundException("No se encontró el usuario con ID " + idUsuario);
        }
        Integer idCurso = datos.getIdCurso();
        if (idCurso != null && !this.repositorioCurso.existsById(idCurso)) {
            throw new ResourceNotFoundException("No se encontró el curso con ID " + idCurso);
        }
        return this.repositorio.save(datos);
    }

    public List<Matricula> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Matricula buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la matrícula con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la matrícula con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}