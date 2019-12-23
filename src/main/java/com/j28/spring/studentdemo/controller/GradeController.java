package com.j28.spring.studentdemo.controller;

import com.j28.spring.studentdemo.model.Grade;
import com.j28.spring.studentdemo.model.GradeSubject;
import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.service.GradeService;
import com.j28.spring.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String submitGradeForm(@RequestParam(name = "studentId") Long studentId,
                                  Grade grade) throws IllegalAccessException {
        Optional<Student> student = studentService.find(studentId);

        student.map(s -> {
            grade.setStudent(s);
            gradeService.save(grade);
            s.getGrades().add(grade);
            studentService.save(s);

            return s;

        }).orElseThrow(() -> new IllegalAccessException("studentId parameter invalid"));

        return "redirect:/grade/list?studentId=" + studentId;
    }


    @GetMapping("/grade/list")
    public String listGrades(Model model, @RequestParam(name = "studentId") Long studentId, Student student) {

        List<Grade> grades = gradeService.getAllByStudentId(studentId);
        model.addAttribute("grades", grades);
        model.addAttribute("student", student);
        return "grade-list";
    }

    @GetMapping("/grade/remove")
    public String removeGrade(@RequestParam(name = "gradeId") Long gradeId,
                              @RequestParam(name = "studentId") Long studentId) {
        gradeService.delete(gradeId);
        return "redirect:/grade/list?studentId=" + studentId;
    }

    @GetMapping("/grade/edit")
    public String editGrade(@RequestParam(name = "gradeId") Long gradeId,
            @RequestParam(name = "studentId") Long studentId, Model model) {
        Optional<Grade> byGradeId = gradeService.findById(gradeId);
        if(byGradeId.isPresent()){
            Grade grade = byGradeId.get();

            model.addAttribute("grade", grade);
            model.addAttribute("subjects", GradeSubject.values());
            model.addAttribute("studentId", studentId);
            model.addAttribute("gradeId", gradeId);

            return "grade-form";
        }
        return "redirect:/grade/list?studentId=" + studentId;
    }

}

