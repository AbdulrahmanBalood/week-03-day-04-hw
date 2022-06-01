package com.example.school.contollers;

import com.example.school.DTO.TeacherDTO;
import com.example.school.models.Student;
import com.example.school.models.Teacher;
import com.example.school.service.StudentService;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }
    @PostMapping
    public ResponseEntity addTeacher(@RequestBody @Valid TeacherDTO teacher){
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body("Teacher added");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id){
        Teacher teacher = teacherService.getOneTeacher(id);
        return ResponseEntity.status(200).body(teacher);
    }
    @PostMapping("/teacheraddclass/{classID}/{teacherID}")
    public ResponseEntity addClassTeacher(@PathVariable Integer classID,@PathVariable Integer teacherID){
        teacherService.addTeacherClass(classID,teacherID);
        return ResponseEntity.status(201).body("Class added to teacher set");
    }
}
