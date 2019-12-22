package com.j28.spring.studentdemo.controller;

import com.j28.spring.studentdemo.model.Student;
import com.j28.spring.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //    @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/student/add")
    public String getStudentForm(Model model, Student student) {
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/student/add")
    public String submitStudentForm(Student student) {
        studentService.save(student);

        return "redirect:/student/list";
    }

    @GetMapping("/student/list")
    public String listStudents(Model model) {
        List<Student> studentList = studentService.getAll();
        model.addAttribute("students", studentList);
        return "student-list";
    }

    @GetMapping("/student/edit")
    public String editForm(Model model, @RequestParam(name = "studentId") Long id) {
        Optional<Student> studentOptional = studentService.find(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            model.addAttribute("student", student);
            return "student-form";
        }
        return "redirect:/student/list";
    }

    @GetMapping("/student/remove")
    public String deleteStudent(@RequestParam(name = "studentId") Long id){
        studentService.delete(id);

        return "redirect:/student/list";
    }
}
