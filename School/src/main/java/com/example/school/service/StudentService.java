package com.example.school.service;

import com.example.school.DTO.StudentDTO;
import com.example.school.exceptions.IdNotFoundException;
import com.example.school.models.Classroom;
import com.example.school.models.Student;
import com.example.school.repository.ClassRoomRepository;
import com.example.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassService classService;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addStudent(StudentDTO studentDTO){
        Student student = new Student(null,studentDTO.getName(),studentDTO.getAge(),studentDTO.getMajor(),null);
        Set<Classroom> classrooms = new HashSet<>();
        if(!classService.findClassById(studentDTO.getClassID())){
            throw new IdNotFoundException("No class room has this id");
        }
        Classroom classroom = classService.getOneClass(studentDTO.getClassID());
        Set<Student> students = classroom.getStudentSet();
        if(students == null){
            students = new HashSet<>();
        }
        students.add(student);
        classroom.setStudentSet(students);
        classrooms.add(classroom);
        student.setClassroomSet(classrooms);
        System.out.println("class set from print "+student.getClassroomSet().toString());
        studentRepository.save(student);
    }
    public Student getOneStudent(Integer id){
        return studentRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Invalid ID") );

    }
    public void addStudentClass(Integer ClassID,Integer studentID){
        if(!classService.findClassById(ClassID)){
            throw new IdNotFoundException("class id not valid");
        }
        Student student = getOneStudent(studentID);
        Set<Classroom> classrooms = student.getClassroomSet();
        Classroom classroom = classService.getOneClass(ClassID);
        classrooms.add(classroom);
        Set<Student> students = classroom.getStudentSet();
        students.add(student);
        studentRepository.save(student);
        classService.addClass(classroom);

    }
}
