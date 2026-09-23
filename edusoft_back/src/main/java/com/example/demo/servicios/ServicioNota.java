package com.example.demo.servicios;


import com.example.demo.modelos.Nota;
import com.example.demo.repositorios.IRepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioNota {
    @Autowired
    IRepositorioNota repositorio;

    public Nota guardar (Nota datos) throws Exception{
        if (datos.getNota() == null) {
            throw new Exception("La nota no puede ser nula");
        }

        if (datos.getNota() < 0 || datos.getNota() > 5) {
            throw  new Exception("La nota debe estar entre 0 y 5");
        }

        datos.setFechaExamen(LocalDate.now());
        return this.repositorio.save(datos);
    }

    public List<Nota> buscarTodos(){
        return this.repositorio.findAll();
    }

    public List<Nota> buscarPorEmail(String email){
        return this.repositorio.findByEmailEstudiante(email);
    }

    public void eliminar(Integer id) throws Exception {
        if (this.repositorio.existsById(id)) {
            this.repositorio.deleteById(id);
        } else {
            throw new Exception("No se encontró la nota");
        }
    }

    public Nota buscarPorId(Integer id)throws Exception{
        //1. Para buscar por Id, primero debo utilizar la clase optional de Java
        Optional<Nota> notaBuscada = this.repositorio.findById(id);
        if (notaBuscada.isPresent()){
            return notaBuscada.get();
        } else {
            throw new Exception("No se encontro la nota buscada");
        }
    }
}
