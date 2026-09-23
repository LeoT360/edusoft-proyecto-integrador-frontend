package com.example.demo.modelos;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreEstudiante;
    private String codigoEstudiante;
    private String emailEstudiante;
    private String estadoMatricula;
    private String nombreMateria;
    private String tipoExamen;
    private Integer edadEstudiante;
    private Integer añoInicio;
    private Integer numeroMaterias;
    private Integer numeroAsistencias;
    private Integer idNota;
    private Boolean estado;
    private Boolean repitente;
    private Double promedio;
    private Double nota;
    private LocalDate fechaExamen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getEmailEstudiante() {
        return emailEstudiante;
    }

    public void setEmailEstudiante(String emailEstudiante) {
        this.emailEstudiante = emailEstudiante;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public Integer getEdadEstudiante() {
        return edadEstudiante;
    }

    public void setEdadEstudiante(Integer edadEstudiante) {
        this.edadEstudiante = edadEstudiante;
    }

    public Integer getAñoInicio() {
        return añoInicio;
    }

    public void setAñoInicio(Integer añoInicio) {
        this.añoInicio = añoInicio;
    }

    public Integer getNumeroMaterias() {
        return numeroMaterias;
    }

    public void setNumeroMaterias(Integer numeroMaterias) {
        this.numeroMaterias = numeroMaterias;
    }

    public Integer getNumeroAsistencias() {
        return numeroAsistencias;
    }

    public void setNumeroAsistencias(Integer numeroAsistencias) {
        this.numeroAsistencias = numeroAsistencias;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getRepitente() {
        return repitente;
    }

    public void setRepitente(Boolean repitente) {
        this.repitente = repitente;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(LocalDate fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

}
