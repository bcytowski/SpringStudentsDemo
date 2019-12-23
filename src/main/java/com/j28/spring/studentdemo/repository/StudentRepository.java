package com.j28.spring.studentdemo.repository;

import com.j28.spring.studentdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


//    List<Student> findAllByLastName(String lastName);
//
//    @Query(value = "Select * from... where ?1", nativeQuery = true)
//    List<Student> mojaCoolQuerka(String lastName);
}
