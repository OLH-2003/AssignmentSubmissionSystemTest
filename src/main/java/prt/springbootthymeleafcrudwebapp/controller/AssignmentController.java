package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Assignment;
import prt.springbootthymeleafcrudwebapp.service.AssignmentService;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public String listAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "assignments/list";
    }

    @GetMapping("/new")
    public String createAssignmentForm(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "assignments/new";
    }

    @PostMapping
    public String saveAssignment(@ModelAttribute("assignment") Assignment assignment) {
        assignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String editAssignmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", assignmentService.getAssignmentById(id));
        return "assignments/edit";
    }

    @PostMapping("/{id}")
    public String updateAssignment(@PathVariable Long id, @ModelAttribute("assignment") Assignment assignment) {
        Assignment existingAssignment = assignmentService.getAssignmentById(id);
        existingAssignment.setTitle(assignment.getTitle());
        existingAssignment.setDescription(assignment.getDescription());
        existingAssignment.setDueDate(assignment.getDueDate());
        existingAssignment.setCourseId(assignment.getCourseId());
        existingAssignment.setStaffId(assignment.getStaffId());
        assignmentService.updateAssignment(existingAssignment);
        return "redirect:/assignments";
    }

    @GetMapping("/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return "redirect:/assignments";
    }
}
