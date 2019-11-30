package com.j28.spring.studentdemo.controller;

import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //    @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String getIndexPage(Model model,
                               @RequestParam(name = "myName") String name){
        model.addAttribute("nameToPass", name);
        return "index";
    }

    @GetMapping("/student/add")
    public String getStudentForm(Model model, Student student){
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/student/add")
    public String submitStudentForm(Student student){
        studentService.save(student);

        return "redirect:/student/list";
    }

    @GetMapping("/student/list")
    public String listStudents(Model model){
        List<Student> studentList = studentService.getAll();
        model.addAttribute("students", studentList);
        return "student-list";
    }
}
