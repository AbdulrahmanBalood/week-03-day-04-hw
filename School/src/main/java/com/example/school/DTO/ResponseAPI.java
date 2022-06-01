package com.example.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ResponseAPI {
    private String message;
    private Integer statusCode;
}
