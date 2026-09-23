package com.example.demo.repositorios;

import com.example.demo.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ✅ REESCRITO: eliminados los @Procedure que requerían stored procedures
//    inexistentes en H2. Ahora usa JPA estándar igual que los demás módulos
//    (IRepositorioProfesor, IRepositorioCurso, IRepositorioReporte, etc.)
//
//    Los métodos findAll(), save() y findById() heredados de JpaRepository
//    reemplazan completamente a listarAsistencias(), crearAsistencia() y buscarPorId().

@Repository
public interface IRepositorioAsistencia extends JpaRepository<Asistencia, Integer> {
    // No se necesitan métodos adicionales por ahora.
    // JpaRepository ya provee:
    //   save(entity)       → crear / actualizar
    //   findAll()          → listar todas
    //   findById(id)       → buscar por id (retorna Optional<Asistencia>)
    //   deleteById(id)     → eliminar
}
