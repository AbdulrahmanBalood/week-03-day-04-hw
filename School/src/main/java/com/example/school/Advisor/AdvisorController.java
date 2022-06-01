package com.example.school.Advisor;

import com.example.school.DTO.ResponseAPI;
import com.example.school.exceptions.IdNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvisorController {
    Logger logger = LoggerFactory.getLogger(AdvisorController.class);
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity idNotFound(IdNotFoundException e){
        logger.error("method idNotFound in the advisor controller is used");
        return ResponseEntity.status(400).body(new ResponseAPI(e.getMessage(),400));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handelExceptionClass(Exception exception){
        logger.error("handelExceptionClass was used");
        return ResponseEntity.status(500).body(new ResponseAPI("Server error",500));
    }
}
