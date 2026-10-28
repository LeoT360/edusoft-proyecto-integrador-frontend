package com.example.demo.application.service;

import com.example.demo.infrastructure.repository.IRepositorioReporte;
import com.example.demo.domain.models.Reporte;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioReporte {

    //El servicio utiliza al repositorio
    //A este uso se le llama inyeccion de dependencia

    @Autowired
    IRepositorioReporte repositorio;

    //Se crean funciones publicas para activar cada consulta que se quiera ofrecer
    public Reporte guardar(Reporte datos){
        return this.repositorio.save(datos);
    }

    public List<Reporte> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Reporte buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el reporte con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el reporte con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}
