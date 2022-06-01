package com.example.school.service;

import com.example.school.DTO.TeacherDTO;
import com.example.school.exceptions.IdNotFoundException;
import com.example.school.models.Classroom;
import com.example.school.models.Student;
import com.example.school.models.Teacher;
import com.example.school.repository.ClassRoomRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final ClassService classService;


    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }
    public void addTeacher(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher(null,teacherDTO.getName(),null);
        Set<Classroom> classrooms = new HashSet<>();
        if(!classService.findClassById(teacherDTO.getClassID())){
            throw new IdNotFoundException("No class room has this id");
        }
        Classroom classroom = classService.getOneClass(teacherDTO.getClassID());
        classrooms.add(classroom);
        teacher.setClassroomSet(classrooms);
        teacherRepository.save(teacher);
    }
    public Teacher getOneTeacher(Integer id){
        return teacherRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Invalid ID") );

    }
    public void addTeacherClass(Integer ClassID,Integer teacherID){
        if(!classService.findClassById(ClassID)){
            throw new IdNotFoundException("class id not valid");
        }
        Teacher teacher = getOneTeacher(teacherID);
        Set<Classroom> classrooms = teacher.getClassroomSet();
        Classroom classroom = classService.getOneClass(ClassID);
        classrooms.add(classroom);
        teacherRepository.save(teacher);

    }
}
