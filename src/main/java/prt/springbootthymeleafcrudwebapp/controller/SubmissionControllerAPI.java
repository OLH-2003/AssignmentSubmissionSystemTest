package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Assignment;
import prt.springbootthymeleafcrudwebapp.model.Submission;
import prt.springbootthymeleafcrudwebapp.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionControllerAPI {

    @Autowired
    private SubmissionService submissionService;

    // Get all submissions
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignmentID(@PathVariable("assignmentId") long assignmentId) {
        List<Submission> submissions = submissionService.getSubmissionsByAssignmentId(assignmentId);
        if (submissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(submissions);
    }

    // Get submission by ID
    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable("id") long id) {
        return submissionService.getSubmissionById(id);
    }

    // Create a new submission
    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.saveSubmission(submission);
    }

    // Update an existing submission
    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable("id") long id, @RequestBody Submission submission) {
        Submission existingSubmission = submissionService.getSubmissionById(id);
        existingSubmission.setAssignmentId(submission.getAssignmentId());
        existingSubmission.setStudentId(submission.getStudentId());
        existingSubmission.setSubmissionTime(submission.getSubmissionTime());
        existingSubmission.setFile(submission.getFile());
        return submissionService.updateSubmission(existingSubmission);
    }

    // Delete a submission by ID
    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable("id") long id) {
        submissionService.deleteSubmissionById(id);
    }
}
