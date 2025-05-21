package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Submission;
import prt.springbootthymeleafcrudwebapp.service.SubmissionService;

@Controller
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public String viewSubmissionsPage(Model model) {
        model.addAttribute("listSubmissions", submissionService.getAllSubmissions());
        return "submissions/index";
    }

    @GetMapping("/new")
    public String showNewSubmissionForm(Model model) {
        Submission submission = new Submission();
        model.addAttribute("submission", submission);
        return "submissions/new_submission";
    }

    @PostMapping
    public String saveSubmission(@ModelAttribute("submission") Submission submission) {
        submissionService.saveSubmission(submission);
        return "redirect:/submissions";
    }

    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        Submission submission = submissionService.getSubmissionById(id);
        model.addAttribute("submission", submission);
        return "submissions/edit_submission";
    }

    @PostMapping("/{id}")
    public String updateSubmission(@PathVariable("id") long id, @ModelAttribute("submission") Submission submission) {

        Submission existingSubmission = submissionService.getSubmissionById(id);
        existingSubmission.setAssignmentId(submission.getAssignmentId());
        existingSubmission.setStudentId(submission.getStudentId());
        existingSubmission.setSubmissionTime(submission.getSubmissionTime());
        existingSubmission.setFile(submission.getFile());

        submissionService.updateSubmission(existingSubmission);
        return "redirect:/submissions";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubmission(@PathVariable("id") long id) {
        submissionService.deleteSubmissionById(id);
        return "redirect:/submissions";
    }
}
