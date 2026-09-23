package com.example.demo.servicios;

import com.example.demo.modelos.Matricula;
import com.example.demo.repositorios.IRepositorioMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMatricula {

    //el servicio utiliza al repositorio
    //a este uso de se le llama inyeccion de dependencia

    @Autowired
    IRepositorioMatricula repositorio;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer
    public Matricula guardar(Matricula datos){
        return this.repositorio.save(datos);
    }

    public List<Matricula> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Matricula buscarPorId(Integer id)throws Exception{
        //1. para buscar por id primero debo utilizar la clase Optional de java
        Optional<Matricula> matriculaBuscado=this.repositorio.findById(id);
        if (matriculaBuscado.isPresent()){
            return matriculaBuscado.get();
        }else{
            throw new Exception("no se encontro la matricula buscado");
        }
    }
}