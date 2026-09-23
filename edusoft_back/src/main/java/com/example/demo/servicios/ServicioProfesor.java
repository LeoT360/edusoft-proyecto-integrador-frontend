package com.example.demo.servicios;

import com.example.demo.modelos.Profesor;
import com.example.demo.repositorios.IRepositorioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProfesor {

    //El servicio utiliza al repositorio
    // a este uso se le llama inyeccion de dependencia

    //inyectar
    @Autowired
    IRepositorioProfesor repositorio;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer
    //3 funciones
    public Profesor guardar(Profesor datos){
        return this.repositorio.save(datos);
    }

    public List<Profesor> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Profesor buscarPorId(Integer id) throws Exception{ // throws acttivandolo
        //1. Para buscar por id primero debo utilizar la clase optional de java
        Optional<Profesor> profesorBuscado= this.repositorio.findById(id);
        if (profesorBuscado.isPresent()){
            return profesorBuscado.get();
        }else {
            throw new Exception("No se encontró el profesor buscado");
        }
    }

}
