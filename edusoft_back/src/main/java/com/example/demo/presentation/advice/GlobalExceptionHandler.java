package com.example.demo.presentation.advice;

import com.example.demo.domain.exceptions.ResourceAlreadyExistsException;
import com.example.demo.domain.exceptions.BusinessException;
import com.example.demo.domain.exceptions.ResourceConflictException;
import com.example.demo.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.ErrorResponse;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        String message = exception.getCause() != null
                ? exception.getCause().getMessage()
                : exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception) {
        String message = exception.getCause() != null
                ? exception.getCause().getMessage()
                : exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleResourceConflictException(ResourceConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(getMessage(exception, "La operación entra en conflicto con el estado actual del recurso"));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        return ResponseEntity.badRequest()
                .body(getMessage(exception, "La operación no se pudo completar"));
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLockingFailure(OptimisticLockingFailureException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo completar la operación porque el recurso ya no existe o fue modificado por otra solicitud");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidRequest(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(getMessage(exception, "La solicitud contiene datos inválidos"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(getMessage(message, "La solicitud contiene datos inválidos"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(this::formatFieldError)
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(getMessage(message, "La solicitud contiene datos inválidos"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidBody(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body("El cuerpo de la solicitud no es válido");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleInvalidParameterType(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.badRequest()
                .body("El parámetro '" + exception.getName() + "' tiene un formato inválido");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParameter(MissingServletRequestParameterException exception) {
        return ResponseEntity.badRequest()
                .body("Falta el parámetro requerido '" + exception.getParameterName() + "'");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataConflict(DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo guardar o eliminar el registro porque existe un dato duplicado o está relacionado con otros registros");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception exception) {
        if (exception instanceof ErrorResponse errorResponse) {
            String message = errorResponse.getBody().getDetail();
            return ResponseEntity.status(errorResponse.getStatusCode())
                    .body(getMessage(message, errorResponse.getStatusCode().toString()));
        }

        logger.error("Error inesperado al procesar la solicitud", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error interno al procesar la solicitud");
    }

    private String formatFieldError(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }

    private String getMessage(Exception exception, String fallback) {
        return getMessage(exception.getMessage(), fallback);
    }

    private String getMessage(String message, String fallback) {
        return message == null || message.isBlank() ? fallback : message;
    }
}
