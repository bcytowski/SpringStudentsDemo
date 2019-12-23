package com.j28.spring.studentdemo.service;

import com.j28.spring.studentdemo.model.Grade;
import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.repository.GradeRepository;
import com.j28.spring.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class GradeService  {
    @Autowired
    private final GradeRepository gradeRepository;



    public void save(Grade grade){
        gradeRepository.save(grade);
    }

    public List<Grade> getAllByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public void delete(Long gradeId){
        Optional<Grade> gradeByIdOptional = findById(gradeId);
        if(gradeByIdOptional.isPresent()){
            gradeRepository.delete(gradeByIdOptional.get());
        }
        else System.err.println("couldn't find specific grade");
    }

    public Optional<Grade> findById (Long gradeId) {
        Optional<Grade> byId = gradeRepository.findById(gradeId);
        return byId;
    }
}
