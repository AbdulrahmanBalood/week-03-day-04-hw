package com.example.school.service;

import com.example.school.exceptions.IdNotFoundException;
import com.example.school.models.Classroom;
import com.example.school.models.Teacher;
import com.example.school.repository.ClassRoomRepository;
import com.example.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRoomRepository classRoomRepository;

    public List<Classroom> getClasses(){
        return classRoomRepository.findAll();
    }
    public void addClass(Classroom classroom){
        classRoomRepository.save(classroom);
    }
    public Classroom getOneClass(Integer id){
        return classRoomRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Invalid ID") );

    }
    public boolean findClassById(Integer id){
        Optional<Classroom> classroom = classRoomRepository.findById(id);
        if(classroom.isPresent())
            return true;
        return false;

    }
}
