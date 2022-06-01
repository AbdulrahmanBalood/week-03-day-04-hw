package com.example.school.contollers;

import com.example.school.DTO.StudentDTO;
import com.example.school.models.Classroom;
import com.example.school.models.Student;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid StudentDTO student){
        studentService.addStudent(student);
        return ResponseEntity.status(201).body("Student added");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id){
        Student student = studentService.getOneStudent(id);
        return ResponseEntity.status(200).body(student);
    }
    @PostMapping("/addclass/{classID}/{studentID}")
    public ResponseEntity addStudentClass(@PathVariable Integer classID,@PathVariable Integer studentID){
        studentService.addStudentClass(classID,studentID);
        return ResponseEntity.status(201).body("Classroom added to student");
    }
}
