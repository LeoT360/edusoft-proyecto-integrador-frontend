package com.example.demo.servicios;

import com.example.demo.modelos.Notificacion;
import com.example.demo.repositorios.IRepositorioNotificacion;
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
    // Se crean funciones públicas para activar cada consulta que se quiera ofrecer
    /**
     * Guardar una nueva notificación o actualizar una existente
     * @param datos Notificación a guardar
     * @return Notificación guardada con ID asignado
     */
    public Notificacion guardar(Notificacion datos) {
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
    public Notificacion buscarPorId(Integer id) throws Exception {
        // 1. Para buscar por ID primero debemos utilizar la clase Optional de Java
        Optional<Notificacion> notificacionBuscada = this.repositorio.findById(id);

        // 2. Verificar si la notificación existe
        if (notificacionBuscada.isPresent()) {
            return notificacionBuscada.get();
        } else {
            throw new Exception("No se encontró la notificación buscada");
        }
    }
}