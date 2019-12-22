package com.j28.spring.studentdemo.service;

import com.j28.spring.studentdemo.model.Grade;
import com.j28.spring.studentdemo.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GradeService  {
    private final GradeRepository gradeRepository;

    public void save(Grade grade){
        gradeRepository.save(grade);
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }
}
