package com.example.school.contollers;

import com.example.school.models.Classroom;
import com.example.school.models.Teacher;
import com.example.school.service.ClassService;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;
    @GetMapping
    public ResponseEntity<List<Classroom>> getClasses(){
        return ResponseEntity.status(200).body(classService.getClasses());
    }
    @PostMapping
    public ResponseEntity addClass(@RequestBody @Valid Classroom classroom){
        classService.addClass(classroom);
        return ResponseEntity.status(201).body("Classroom added");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassById(@PathVariable Integer id){
        Classroom cR = classService.getOneClass(id);
        return ResponseEntity.status(200).body(cR);
    }
}
