package com.example.demo.servicios;

import com.example.demo.modelos.Usuario;
import com.example.demo.repositorios.IRepositorioUsuario;
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

    public List<Usuario> BuscarTodos(){

        return this.repositorio.findAll();

    }

    public Usuario BuscarPorId(Integer id)throws Exception{

        //1-Para buscar por id primero debo utilizar la clase optional de java
        Optional<Usuario> usuarioBuscado=this.repositorio.findById(id);
        if (usuarioBuscado.isPresent()) {

            return usuarioBuscado.get();

        }else {

            throw new Exception("No se encontro el usuario buscado");

        }

    }

}