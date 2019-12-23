package com.j28.spring.studentdemo.repository;

import com.j28.spring.studentdemo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudentId(Long studentId);

    Optional<Grade> findById(Long gradeId);





}

