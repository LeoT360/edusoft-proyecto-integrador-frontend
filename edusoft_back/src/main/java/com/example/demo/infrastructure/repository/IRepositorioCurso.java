package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioCurso extends JpaRepository<Curso, Integer> {
}
