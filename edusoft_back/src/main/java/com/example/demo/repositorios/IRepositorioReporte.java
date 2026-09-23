package com.example.demo.repositorios;

import com.example.demo.modelos.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioReporte extends JpaRepository<Reporte,Integer> {



}