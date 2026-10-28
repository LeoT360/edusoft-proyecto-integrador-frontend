package com.example.demo.application.service;

import com.example.demo.domain.models.Usuario;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import com.example.demo.infrastructure.repository.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServicioUsuario {

    //El servicio utiliza al repositorio
    //A este uso se le llama inyeccion de dependencias

    @Autowired
    IRepositorioUsuario repositorio;

    //Se crean funciones publlicas para activar cada consulta que se quiera ofrecer

    public Usuario guardar(Usuario datos){

        return this.repositorio.save(datos);

    }

    public List<Usuario> buscarTodos(){

        return this.repositorio.findAll();

    }

    public Usuario buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con ID " + id));
    }

    public void eliminar(Integer id) {
        if (!this.repositorio.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el usuario con ID " + id);
        }
        this.repositorio.deleteById(id);
    }
}