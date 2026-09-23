package com.example.demo.repositorios;

import com.example.demo.modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioCurso extends JpaRepository<Curso, Integer> {
}
