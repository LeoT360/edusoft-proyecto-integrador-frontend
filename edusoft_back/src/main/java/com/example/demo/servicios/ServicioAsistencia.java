package com.example.demo.servicios;

import com.example.demo.modelos.Asistencia;
import com.example.demo.repositorios.IRepositorioAsistencia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAsistencia {

    private final IRepositorioAsistencia repository;

    public ServicioAsistencia(IRepositorioAsistencia repository) {
        this.repository = repository;
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

        // save() crea un nuevo registro y retorna la entidad con el ID generado
        return repository.save(asistencia);
    }

    // ✅ REESCRITO: usa findAll() en lugar del stored procedure listarAsistencias()
    public List<Asistencia> obtenerTodas() {
        return repository.findAll();
    }

    // ✅ REESCRITO: usa findById() (retorna Optional) en lugar del stored procedure buscarPorId()
    public Asistencia obtenerPorId(Integer id) {
        Optional<Asistencia> resultado = repository.findById(id);
        if (resultado.isPresent()) {
            return resultado.get();
        } else {
            throw new RuntimeException("Asistencia con ID " + id + " no encontrada");
        }
    }
}
