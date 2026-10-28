package com.example.demo.application.service;

import com.example.demo.domain.models.Asistencia;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioAsistencia;
import com.example.demo.infrastructure.repository.IRepositorioCurso;
import com.example.demo.infrastructure.repository.IRepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAsistencia {

    private final IRepositorioAsistencia repository;
    private final IRepositorioUsuario repositorioUsuario;
    private final IRepositorioCurso repositorioCurso;

    public ServicioAsistencia(
            IRepositorioAsistencia repository,
            IRepositorioUsuario repositorioUsuario,
            IRepositorioCurso repositorioCurso) {
        this.repository = repository;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioCurso = repositorioCurso;
    }

    // ✅ REESCRITO: ya no llama a repository.crearAsistencia() (stored procedure)
    //    Ahora usa repository.save() — igual que guardar() en ServicioProfesor,
    //    ServicioCurso, ServicioReporte, etc.
    public Asistencia crear(Asistencia asistencia) {
        if (asistencia.getNombrePersona() == null || asistencia.getNombrePersona().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (asistencia.getFecha() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        if (asistencia.getHoraEntrada() == null) {
            throw new IllegalArgumentException("La hora de entrada es obligatoria");
        }
        if (asistencia.getAsistio() == null) {
            asistencia.setAsistio(false); // valor por defecto si no se envía
        }

        Integer idUsuario = asistencia.getIdUsuario();
        if (idUsuario != null && !repositorioUsuario.existsById(idUsuario)) {
            throw new ResourceNotFoundException("No se encontró el usuario con ID " + idUsuario);
        }
        Integer idCurso = asistencia.getIdCurso();
        if (idCurso != null && !repositorioCurso.existsById(idCurso)) {
            throw new ResourceNotFoundException("No se encontró el curso con ID " + idCurso);
        }

        return repository.save(asistencia);
    }

    // ✅ REESCRITO: usa findAll() en lugar del stored procedure listarAsistencias()
    public List<Asistencia> obtenerTodas() {
        return repository.findAll();
    }

    // ✅ REESCRITO: usa findById() (retorna Optional) en lugar del stored procedure buscarPorId()
    public Asistencia obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la asistencia con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la asistencia con ID " + id);
        }
        repository.deleteById(id);
    }
}
