package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Student;
import prt.springbootthymeleafcrudwebapp.service.StudentService;

@Controller
@RequestMapping("/api/students")
public class StudentControllerAPI {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/index";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "students/edit";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable long id, @ModelAttribute("student") Student updatedStudent) {
        Student existingStudent = studentService.getStudentById(id);

        existingStudent.setFullName(updatedStudent.getFullName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPasswordHash(updatedStudent.getPasswordHash());
        existingStudent.setStudentId(updatedStudent.getStudentId());

        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}