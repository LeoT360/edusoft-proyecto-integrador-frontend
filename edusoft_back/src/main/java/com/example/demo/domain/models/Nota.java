package com.example.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
    @NotNull(message = "La nota no puede ser nula")
    @DecimalMin(value = "0.0", message = "La nota debe estar entre 0 y 5")
    @DecimalMax(value = "5.0", message = "La nota debe estar entre 0 y 5")
    private Double nota;
    private LocalDate fechaExamen;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Transient
    @Positive(message = "El ID del usuario debe ser positivo")
    public Integer getIdUsuario() {
        return usuario == null ? null : usuario.getId();
    }

    public void setIdUsuario(Integer idUsuario) {
        this.usuario = idUsuario == null ? null : new Usuario();
        if (this.usuario != null) {
            this.usuario.setId(idUsuario);
        }
    }

    @Transient
    @Positive(message = "El ID del curso debe ser positivo")
    public Integer getIdCurso() {
        return curso == null ? null : curso.getId();
    }

    public void setIdCurso(Integer idCurso) {
        this.curso = idCurso == null ? null : new Curso();
        if (this.curso != null) {
            this.curso.setId(idCurso);
        }
    }

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
