package com.j28.spring.studentdemo.service;

import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
