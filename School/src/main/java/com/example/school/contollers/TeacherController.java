package com.example.school.contollers;

import com.example.school.DTO.ResponseAPI;
import com.example.school.DTO.TeacherDTO;
import com.example.school.models.Student;
import com.example.school.models.Teacher;
import com.example.school.service.StudentService;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    Logger logger = LoggerFactory.getLogger(TeacherController.class);
    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        logger.info("method getTeachers in the controller is used");
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }
    @PostMapping
    public ResponseEntity<ResponseAPI> addTeacher(@RequestBody @Valid TeacherDTO teacher){
        logger.info("method addTeachers in the controller is used");
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(new ResponseAPI("Teacher added",20));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id){
        logger.info("method getTeachersById in the controller is used");
        Teacher teacher = teacherService.getOneTeacher(id);
        return ResponseEntity.status(200).body(teacher);
    }
    @PostMapping("/teacheraddclass/{classID}/{teacherID}")
    public ResponseEntity<ResponseAPI> addClassTeacher(@PathVariable Integer classID,@PathVariable Integer teacherID){
        logger.info("method addClassTeacher in the controller is used");
        teacherService.addTeacherClass(classID,teacherID);
        return ResponseEntity.status(201).body(new ResponseAPI("Class added to teacher set",201));
    }
}
