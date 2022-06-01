package com.example.school.contollers;

import com.example.school.DTO.ResponseAPI;
import com.example.school.models.Classroom;
import com.example.school.models.Teacher;
import com.example.school.service.ClassService;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    Logger logger = LoggerFactory.getLogger(ClassController.class);
    private final ClassService classService;
    @GetMapping
    public ResponseEntity<List<Classroom>> getClasses(){
        logger.info("method getClasses in the controller is used");
        return ResponseEntity.status(200).body(classService.getClasses());
    }
    @PostMapping
    public ResponseEntity addClass(@RequestBody @Valid Classroom classroom){
        logger.info("method addClass in the controller is used");
        classService.addClass(classroom);
        return ResponseEntity.status(201).body(new ResponseAPI("Classroom added",201));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassById(@PathVariable Integer id){
        logger.info("method getClassById in the controller is used");
        Classroom cR = classService.getOneClass(id);
        return ResponseEntity.status(200).body(cR);
    }
}
