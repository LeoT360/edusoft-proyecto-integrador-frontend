package com.example.demo.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// ✅ LIMPIADO: eliminadas las anotaciones @NamedStoredProcedureQueries y
//    @NamedStoredProcedureQuery que referenciaban procedimientos almacenados
//    inexistentes en H2. La entidad queda limpia igual que Profesor.java,
//    Curso.java, Reporte.java, etc.

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombrePersona;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaEntrada;

    @Column(nullable = false)
    private Boolean asistio;

    // Constructor vacío obligatorio para JPA
    public Asistencia() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public Boolean getAsistio() { return asistio; }
    public void setAsistio(Boolean asistio) { this.asistio = asistio; }
}
