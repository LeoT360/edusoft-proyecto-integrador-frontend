package com.example.demo.presentation.advice;

import com.example.demo.domain.exceptions.ResourceConflictException;
import com.example.demo.domain.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void returnsBusinessConflictMessage() {
        var response = handler.handleResourceConflictException(
                new ResourceConflictException("El correo ya está registrado"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("El correo ya está registrado", response.getBody());
    }

    @Test
    void returnsBusinessExceptionMessage() {
        var response = handler.handleBusinessException(new BusinessException("La operación no es válida"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("La operación no es válida", response.getBody());
    }

    @Test
    void returnsReadableDataIntegrityConflictMessage() {
        var response = handler.handleDataConflict(new DataIntegrityViolationException("database details"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(
                "No se pudo guardar o eliminar el registro porque existe un dato duplicado o está relacionado con otros registros",
                response.getBody());
        assertFalse(response.getBody().contains("database details"));
    }

    @Test
    void hidesInternalExceptionDetailsFromResponse() {
        var response = handler.handleUnexpectedException(new IllegalStateException("internal details"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocurrió un error interno al procesar la solicitud", response.getBody());
        assertFalse(response.getBody().contains("internal details"));
    }

    @Test
    void preservesStatusAndMessageFromFrameworkExceptions() {
        var response = handler.handleUnexpectedException(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "La ruta solicitada no existe"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("La ruta solicitada no existe", response.getBody());
    }
}
