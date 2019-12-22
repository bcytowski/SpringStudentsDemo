package com.j28.spring.studentdemo.controller;

import com.j28.spring.studentdemo.model.Grade;
import com.j28.spring.studentdemo.model.GradeSubject;
import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.service.GradeService;
import com.j28.spring.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;

    @GetMapping("/grade/add")
    public String getGradeForm(Model model, Grade grade, @RequestParam(name = "studentId") Long studentId) {
        model.addAttribute("subjects", GradeSubject.values());
        model.addAttribute("grade", grade);
        model.addAttribute("studentId", studentId);
        return "grade-form";
    }

    @PostMapping("/grade/add")
    public String submitGradeForm(Grade grade) {

            gradeService.save(grade);
            return "redirect:/grade/list?studentId=" + grade.getStudent().getId();
        }

        
    @GetMapping("/grade/list")
    public String listGrades(Model model, @RequestParam(name = "studentId") Long studentId) {
        Optional<Student> optionalStudent = studentService.find(studentId);
            if(optionalStudent.isPresent()){
                Student owner = optionalStudent.get();
                model.addAttribute("grades",owner.getGrades());
                return "redirect:/grade/list?studentId=" + owner.getId();
            }
        return "student-list";
    }
}

