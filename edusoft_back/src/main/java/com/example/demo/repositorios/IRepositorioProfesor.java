package com.example.demo.repositorios;

import com.example.demo.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioProfesor  extends JpaRepository<Profesor, Integer> {

}
