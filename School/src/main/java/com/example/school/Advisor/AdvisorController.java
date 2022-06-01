package com.example.school.Advisor;

import com.example.school.exceptions.IdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvisorController {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity idNotFound(IdNotFoundException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
