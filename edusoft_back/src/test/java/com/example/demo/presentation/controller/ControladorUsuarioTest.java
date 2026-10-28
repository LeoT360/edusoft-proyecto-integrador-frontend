package com.example.demo.presentation.controller;

import com.example.demo.application.service.ServicioUsuario;
import com.example.demo.domain.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControladorUsuarioTest {

    @Test
    void postIgnoresClientIdAndLetsDatabaseGenerateIt() throws Exception {
        Usuario datos = new ObjectMapper().readValue(
                """
                {
                  "id": 4,
                  "nombre": "Julius",
                  "correo": "julius@correo.com",
                  "contraseña": "2008",
                  "rol": "",
                  "telefono": "3000000000"
                }
                """,
                Usuario.class);

        assertNull(datos.getId());

        ServicioUsuario servicio = mock(ServicioUsuario.class);
        when(servicio.guardar(any(Usuario.class))).thenAnswer(invocacion -> invocacion.getArgument(0));
        ControladorUsuario controlador = new ControladorUsuario();
        controlador.servicio = servicio;

        controlador.guardar(datos);

        assertNull(datos.getId());
    }
}
