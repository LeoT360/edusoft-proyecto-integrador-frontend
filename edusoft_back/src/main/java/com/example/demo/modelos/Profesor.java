package com.example.demo.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreCompleto;
    private Integer edad;
    private String tipoIdentificacion;
    private String numeroDocumento;
    private String correoElectronico;
    private String direccion;           // ← sin tilde para evitar problemas de encoding

    // ✅ CORREGIDO: String en lugar de Integer
    // Integer explota con números de 10 dígitos y no acepta guiones ni espacios
    private String celular;

    private String estadoCivil;
    private String genero;

    // ✅ CORREGIDO: TEXT para soportar textos largos
    @Column(columnDefinition = "TEXT")
    private String perfilProfesional;

    // ✅ CORREGIDO: TEXT para soportar URLs largas o base64
    @Column(columnDefinition = "TEXT")
    private String hojaDeVida;

    // ✅ CORREGIDO: TEXT - si se sube como base64 supera VARCHAR(255) fácilmente
    @Column(columnDefinition = "TEXT")
    private String foto;

    private String idiomasDominados;
    private String estudiosObtenidos;
    private String nivelAcademico;
    private Integer escalafon;
    private Integer anosExperiencia;
    private String areasAsignadas;
    private Boolean vigencia;
    private String tipoContrato;
    private String jornadaLaboral;
    private String horasSemanales;
    private String fechaIngreso;

    // ==================== GETTERS Y SETTERS ====================

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // ✅ String getter/setter
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getPerfilProfesional() { return perfilProfesional; }
    public void setPerfilProfesional(String perfilProfesional) { this.perfilProfesional = perfilProfesional; }

    public String getHojaDeVida() { return hojaDeVida; }
    public void setHojaDeVida(String hojaDeVida) { this.hojaDeVida = hojaDeVida; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getIdiomasDominados() { return idiomasDominados; }
    public void setIdiomasDominados(String idiomasDominados) { this.idiomasDominados = idiomasDominados; }

    public String getEstudiosObtenidos() { return estudiosObtenidos; }
    public void setEstudiosObtenidos(String estudiosObtenidos) { this.estudiosObtenidos = estudiosObtenidos; }

    public String getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(String nivelAcademico) { this.nivelAcademico = nivelAcademico; }

    public Integer getEscalafon() { return escalafon; }
    public void setEscalafon(Integer escalafon) { this.escalafon = escalafon; }

    public Integer getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(Integer anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public String getAreasAsignadas() { return areasAsignadas; }
    public void setAreasAsignadas(String areasAsignadas) { this.areasAsignadas = areasAsignadas; }

    public Boolean getVigencia() { return vigencia; }
    public void setVigencia(Boolean vigencia) { this.vigencia = vigencia; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public String getJornadaLaboral() { return jornadaLaboral; }
    public void setJornadaLaboral(String jornadaLaboral) { this.jornadaLaboral = jornadaLaboral; }

    public String getHorasSemanales() { return horasSemanales; }
    public void setHorasSemanales(String horasSemanales) { this.horasSemanales = horasSemanales; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
}
