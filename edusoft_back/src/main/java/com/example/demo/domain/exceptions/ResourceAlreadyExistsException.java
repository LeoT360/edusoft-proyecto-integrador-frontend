package com.example.demo.domain.exceptions;

public class ResourceAlreadyExistsException extends BusinessException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
