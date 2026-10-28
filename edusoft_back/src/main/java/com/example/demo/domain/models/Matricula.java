package com.example.demo.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El documento es obligatorio")
    private String documento;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;
    @NotNull(message = "La fecha de matrícula es obligatoria")
    private LocalDate fechaMatricula;
    private Double valorMatricula;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Double getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(Double valorMatricula) {
        this.valorMatricula = valorMatricula;
    }
}
