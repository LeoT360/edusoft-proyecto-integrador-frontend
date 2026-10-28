package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioMatricula extends JpaRepository<Matricula, Integer> {
}