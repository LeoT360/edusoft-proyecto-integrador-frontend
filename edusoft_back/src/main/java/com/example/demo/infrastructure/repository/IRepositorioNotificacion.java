package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioNotificacion extends JpaRepository<Notificacion, Integer>{
    // Métodos personalizados si los necesitas en el futuro
}