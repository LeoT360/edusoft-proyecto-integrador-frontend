package com.example.demo.application.service;

import com.example.demo.domain.models.Nota;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioCurso;
import com.example.demo.infrastructure.repository.IRepositorioNota;
import com.example.demo.infrastructure.repository.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioNota {
    @Autowired
    IRepositorioNota repositorio;

    @Autowired
    IRepositorioUsuario repositorioUsuario;

    @Autowired
    IRepositorioCurso repositorioCurso;

    public Nota guardar (Nota datos) {
        if (datos.getNota() == null) {
            throw new IllegalArgumentException("La nota no puede ser nula");
        }

        if (datos.getNota() < 0 || datos.getNota() > 5) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 5");
        }
        Integer idUsuario = datos.getIdUsuario();
        if (idUsuario != null && !this.repositorioUsuario.existsById(idUsuario)) {
            throw new ResourceNotFoundException("No se encontró el usuario con ID " + idUsuario);
        }
        Integer idCurso = datos.getIdCurso();
        if (idCurso != null && !this.repositorioCurso.existsById(idCurso)) {
            throw new ResourceNotFoundException("No se encontró el curso con ID " + idCurso);
        }

        datos.setFechaExamen(LocalDate.now());
        return this.repositorio.save(datos);
    }

    public List<Nota> buscarTodos(){
        return this.repositorio.findAll();
    }

    public List<Nota> buscarPorEmail(String email){
        return this.repositorio.findByEmailEstudiante(email);
    }

    public void eliminar(Integer id) {
        if (this.repositorio.existsById(id)) {
            this.repositorio.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontró la nota con ID " + id);
        }
    }

    public Nota buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la nota con ID " + id));
    }
}
