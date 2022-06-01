package com.example.school.contollers;

import com.example.school.DTO.ResponseAPI;
import com.example.school.DTO.StudentDTO;
import com.example.school.models.Classroom;
import com.example.school.models.Student;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        logger.info("method getStudent in the controller is used");
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping
    public ResponseEntity<ResponseAPI> addStudent(@RequestBody @Valid StudentDTO student){
        logger.info("method addStudent in the controller is used");
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ResponseAPI("Student added",201));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id){
        logger.info("method getStudentById in the controller is used");
        Student student = studentService.getOneStudent(id);
        return ResponseEntity.status(200).body(student);
    }
    @PostMapping("/addclass/{classID}/{studentID}")
    public ResponseEntity<ResponseAPI> addStudentClass(@PathVariable Integer classID,@PathVariable Integer studentID){
        logger.info("method addStudentClass in the controller is used");
        studentService.addStudentClass(classID,studentID);
        return ResponseEntity.status(201).body(new ResponseAPI("Classroom added to student",201));
    }
}
