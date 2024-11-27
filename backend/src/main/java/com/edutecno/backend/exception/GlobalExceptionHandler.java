package com.edutecno.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MateriaYaAsignada.class)
    public ResponseEntity<String> handlerMateriaYaAsignada(MateriaYaAsignada materia) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(materia.getMessage());
    }
}
