package com.example.demo.modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idRemitente;
    private Integer idDestinatario;
    private String emailRemitente;
    private String emailDestinatario;
    private String asunto;
    private String cuerpoMensaje;
    private String tipoNotificacion;
    private String prioridad;
    private String cursoRelacionado;
    private String categoriaCurso;
    private String fechaEntrega;
    private String fechaCreacion;
    private String fechaEnvio;
    private String horaEnvio;
    private Boolean mensajeEnviado;
    private Boolean mensajeLeido;
    private Boolean mensajeEliminado;
    private Boolean estado;
    private Integer cantidadArchivosAdjuntos;
    private Boolean notificacionEmergente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Integer idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Integer getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Integer idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getCursoRelacionado() {
        return cursoRelacionado;
    }

    public void setCursoRelacionado(String cursoRelacionado) {
        this.cursoRelacionado = cursoRelacionado;
    }

    public String getCategoriaCurso() {
        return categoriaCurso;
    }

    public void setCategoriaCurso(String categoriaCurso) {
        this.categoriaCurso = categoriaCurso;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(String horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public Boolean getMensajeEnviado() {
        return mensajeEnviado;
    }

    public void setMensajeEnviado(Boolean mensajeEnviado) {
        this.mensajeEnviado = mensajeEnviado;
    }

    public Boolean getMensajeLeido() {
        return mensajeLeido;
    }

    public void setMensajeLeido(Boolean mensajeLeido) {
        this.mensajeLeido = mensajeLeido;
    }

    public Boolean getMensajeEliminado() {
        return mensajeEliminado;
    }

    public void setMensajeEliminado(Boolean mensajeEliminado) {
        this.mensajeEliminado = mensajeEliminado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getCantidadArchivosAdjuntos() {
        return cantidadArchivosAdjuntos;
    }

    public void setCantidadArchivosAdjuntos(Integer cantidadArchivosAdjuntos) {
        this.cantidadArchivosAdjuntos = cantidadArchivosAdjuntos;
    }
    public Boolean getNotificacionEmergente() {
        return notificacionEmergente;
    }

    public void setNotificacionEmergente(Boolean notificacionEmergente) {
        this.notificacionEmergente = notificacionEmergente;
    }
}