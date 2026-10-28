package com.example.demo.application.service;

import com.example.demo.domain.models.Notificacion;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioNotificacion;
import com.example.demo.infrastructure.repository.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioNotificacion {
    // El servicio utiliza al repositorio
    // A este uso se le llama inyección de dependencias
    @Autowired
    IRepositorioNotificacion repositorio;

    @Autowired
    IRepositorioUsuario repositorioUsuario;
    // Se crean funciones públicas para activar cada consulta que se quiera ofrecer
    /**
     * Guardar una nueva notificación o actualizar una existente
     * @param datos Notificación a guardar
     * @return Notificación guardada con ID asignado
     */
    public Notificacion guardar(Notificacion datos) {
        Integer idRemitente = datos.getIdRemitente();
        if (idRemitente != null && !this.repositorioUsuario.existsById(idRemitente)) {
            throw new ResourceNotFoundException("No se encontró el usuario remitente con ID " + idRemitente);
        }
        Integer idDestinatario = datos.getIdDestinatario();
        if (idDestinatario != null && !this.repositorioUsuario.existsById(idDestinatario)) {
            throw new ResourceNotFoundException("No se encontró el usuario destinatario con ID " + idDestinatario);
        }
        return this.repositorio.save(datos);
    }
    /**
     * Buscar todas las notificaciones en la base de datos
     * @return Lista de todas las notificaciones
     */
    public List<Notificacion> buscarTodos() {
        return this.repositorio.findAll();
    }
    /**
     * Buscar una notificación por su ID
     * @param id ID de la notificación a buscar
     * @return Notificación encontrada
     * @throws Exception Si no se encuentra la notificación
     */
    public Notificacion buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la notificación con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la notificación con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}