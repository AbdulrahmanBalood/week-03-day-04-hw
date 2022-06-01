package com.example.school.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Age cannot be null")
    private Integer age;
    @NotEmpty(message = "Major cannot be null")
    private String major;
    @ManyToMany(mappedBy = "studentSet")
    Set<Classroom> classroomSet;
}
