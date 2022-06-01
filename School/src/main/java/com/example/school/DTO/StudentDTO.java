package com.example.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data
public class StudentDTO {
   private Integer classID;
    private String name;

    private Integer age;

    private String major;

}
