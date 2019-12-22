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
    private final StudentRepository studentRepository;


    public void save(Grade grade){
        gradeRepository.save(grade);
    }



    public Grade createGrade(Long studentId, Grade grade){
        Set<Grade> grades = new HashSet<>();

        Optional<Student>byId = studentRepository.findById(studentId);
        Student student1 = byId.get();
        grade.setStudent(student1);
        Grade grade1 = gradeRepository.save(grade);
        grades.add(grade1);
        student1.setGrades(grades);
        return grade1;
    }

    public List<Grade> getAllByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
