package com.example.demo.servicios;

import com.example.demo.repositorios.IRepositorioReporte;
import com.example.demo.modelos.Reporte;
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

    public Reporte buscarPorId(Integer id) throws Exception{
        //para buscar por Id primero debo utilizar la clase optional de java
        Optional<Reporte> reporteBuscado=this.repositorio.findById(id);
        if(reporteBuscado.isPresent()){
            return reporteBuscado.get();
        }else {
            throw new Exception("No se encontro el () buscado");
        }
    }

}
