package com.example.demo.repositorios;

import com.example.demo.modelos.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioNotificacion extends JpaRepository<Notificacion, Integer>{
    // Métodos personalizados si los necesitas en el futuro
}