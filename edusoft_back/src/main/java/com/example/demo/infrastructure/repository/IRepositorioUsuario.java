package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioUsuario extends JpaRepository<Usuario,Integer> {



}
