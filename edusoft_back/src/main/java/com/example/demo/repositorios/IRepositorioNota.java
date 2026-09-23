package com.example.demo.repositorios;

import com.example.demo.modelos.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRepositorioNota extends JpaRepository<Nota,Integer> {
    List<Nota> findByEmailEstudiante(String emailEstudiante);
}
