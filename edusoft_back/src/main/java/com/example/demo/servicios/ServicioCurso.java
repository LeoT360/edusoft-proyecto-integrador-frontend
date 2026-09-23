package com.example.demo.servicios;

import com.example.demo.modelos.Curso;
import com.example.demo.repositorios.IRepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServicioCurso {

    // el servicio utiliza al repositorio, a este uso se le llama inyeccion de dependencia

    @Autowired
    IRepositorioCurso repositorio;

    //se crean funciones publicas para activar cada consulta que se quiera ofrecer

    public Curso guardar(Curso datos){
        return this.repositorio.save(datos);
    }

    public List<Curso> buscarTodos(){
        return this.repositorio.findAll();
    }

    public Curso buscarPorId(Integer id) throws Exception{
        //para buscar por id, primero, debo utilizar la clase optional de Java
        Optional<Curso> cursoBuscado = this.repositorio.findById(id);
        if(cursoBuscado.isPresent()){
            return cursoBuscado.get();
        }else{
            throw new Exception("Curso no encontrado, intentelo nuevamente y verifique los parametros");
        }

    }
}