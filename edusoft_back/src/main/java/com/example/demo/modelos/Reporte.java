package com.example.demo.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ── Campos académicos ──
    private Double notaFinal;

    // ✅ CORREGIDO: "desempeño" → "desempeno"
    // Las tildes en nombres de campo Java causan problemas de encoding con JPA/MySQL
    private String desempeno;

    private String asistenciaTotal;
    private String promedioCursos;
    private Double promedioNotaCursos;

    // ── Campos de cursos ──
    private Integer cantidadCursos;
    private String asistenciaCursos;
    private String cursoPopular;
    private String cursoMenosPopular;
    private Double cantidadHorasCurso;

    // ── Campos administrativos ──
    // ✅ CORREGIDO: typo "cantidadUsarios" → "cantidadUsuarios"
    private Integer cantidadUsuarios;
    private Integer cantidadUsuariosCurso;
    private Double promedioUsuariosAprobadosCurso;
    private Double promedioMatricula;
    private String calificacionDocente;

    // ── Tipo de reporte para distinguir académico vs administrativo ──
    private String tipoReporte;   // "ACADEMICO" | "ADMINISTRATIVO"
    private String periodoReporte; // Ej: "2026-1"

    // ── Getters y Setters ──

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }

    public String getDesempeno() { return desempeno; }
    public void setDesempeno(String desempeno) { this.desempeno = desempeno; }

    public String getAsistenciaTotal() { return asistenciaTotal; }
    public void setAsistenciaTotal(String asistenciaTotal) { this.asistenciaTotal = asistenciaTotal; }

    public String getPromedioCursos() { return promedioCursos; }
    public void setPromedioCursos(String promedioCursos) { this.promedioCursos = promedioCursos; }

    public Double getPromedioNotaCursos() { return promedioNotaCursos; }
    public void setPromedioNotaCursos(Double promedioNotaCursos) { this.promedioNotaCursos = promedioNotaCursos; }

    public Integer getCantidadCursos() { return cantidadCursos; }
    public void setCantidadCursos(Integer cantidadCursos) { this.cantidadCursos = cantidadCursos; }

    public String getAsistenciaCursos() { return asistenciaCursos; }
    public void setAsistenciaCursos(String asistenciaCursos) { this.asistenciaCursos = asistenciaCursos; }

    public String getCursoPopular() { return cursoPopular; }
    public void setCursoPopular(String cursoPopular) { this.cursoPopular = cursoPopular; }

    public String getCursoMenosPopular() { return cursoMenosPopular; }
    public void setCursoMenosPopular(String cursoMenosPopular) { this.cursoMenosPopular = cursoMenosPopular; }

    public Double getCantidadHorasCurso() { return cantidadHorasCurso; }
    public void setCantidadHorasCurso(Double cantidadHorasCurso) { this.cantidadHorasCurso = cantidadHorasCurso; }

    public Integer getCantidadUsuarios() { return cantidadUsuarios; }
    public void setCantidadUsuarios(Integer cantidadUsuarios) { this.cantidadUsuarios = cantidadUsuarios; }

    public Integer getCantidadUsuariosCurso() { return cantidadUsuariosCurso; }
    public void setCantidadUsuariosCurso(Integer cantidadUsuariosCurso) { this.cantidadUsuariosCurso = cantidadUsuariosCurso; }

    public Double getPromedioUsuariosAprobadosCurso() { return promedioUsuariosAprobadosCurso; }
    public void setPromedioUsuariosAprobadosCurso(Double v) { this.promedioUsuariosAprobadosCurso = v; }

    public Double getPromedioMatricula() { return promedioMatricula; }
    public void setPromedioMatricula(Double promedioMatricula) { this.promedioMatricula = promedioMatricula; }

    public String getCalificacionDocente() { return calificacionDocente; }
    public void setCalificacionDocente(String calificacionDocente) { this.calificacionDocente = calificacionDocente; }

    public String getTipoReporte() { return tipoReporte; }
    public void setTipoReporte(String tipoReporte) { this.tipoReporte = tipoReporte; }

    public String getPeriodoReporte() { return periodoReporte; }
    public void setPeriodoReporte(String periodoReporte) { this.periodoReporte = periodoReporte; }
}
