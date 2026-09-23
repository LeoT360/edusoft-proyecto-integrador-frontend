package com.example.demo.modelos;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="cursos")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Double duracion;
    private String maestro;
    private Integer estudiantes;
    private String tipoCurso;
    private LocalDate fechaCreacion;
    private Double calificacion;
    private LocalDate fechaFinalizacion;
    private Integer capitulosCurso;
    private Boolean presencialidad;
    private String comentarios;
    private Integer intensidad;
    private Integer lugarRealizacion;
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public Integer getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Integer estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Integer getCapitulosCurso() {
        return capitulosCurso;
    }

    public void setCapitulosCurso(Integer capitulosCurso) {
        this.capitulosCurso = capitulosCurso;
    }

    public Boolean getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(Boolean presencialidad) {
        this.presencialidad = presencialidad;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public Integer getLugarRealizacion() {
        return lugarRealizacion;
    }

    public void setLugarRealizacion(Integer lugarRealizacion) {
        this.lugarRealizacion = lugarRealizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

