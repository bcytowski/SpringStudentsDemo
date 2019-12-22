package com.j28.spring.studentdemo.service;

import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Student> find(Long id) {
        return studentRepository.findById(id);
    }

    public void delete(Long id){
        Optional <Student> optionalStudent = find(id);
        if(optionalStudent.isPresent()){
            studentRepository.delete(optionalStudent.get());
        }else {
            System.err.println("Couldn't find specific student");
        }
    }
}
