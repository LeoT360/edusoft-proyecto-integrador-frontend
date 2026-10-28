package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioProfesor  extends JpaRepository<Profesor, Integer> {

}
