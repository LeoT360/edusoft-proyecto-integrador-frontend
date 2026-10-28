package com.example.demo.infrastructure.repository;

import com.example.demo.domain.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioReporte extends JpaRepository<Reporte,Integer> {



}