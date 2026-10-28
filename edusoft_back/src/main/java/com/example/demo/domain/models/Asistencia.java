package com.example.demo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotBlank(message = "El nombre es obligatorio")
    private String nombrePersona;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La hora de entrada es obligatoria")
    @Column(nullable = false)
    private LocalTime horaEntrada;

    @Column(nullable = false)
    private Boolean asistio;

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
