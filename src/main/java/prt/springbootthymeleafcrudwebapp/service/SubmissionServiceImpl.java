package prt.springbootthymeleafcrudwebapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.springbootthymeleafcrudwebapp.exception.ResourceNotFound;
import prt.springbootthymeleafcrudwebapp.model.Submission;
import prt.springbootthymeleafcrudwebapp.repository.SubmissionRepository;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public Submission getSubmissionById(long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found for id: " + id));
    }

    @Override
    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public void deleteSubmissionById(long id) {
        submissionRepository.deleteById(id);
    }

    @Override
    public Submission updateSubmission(Submission submission) {
        Submission existingSubmission = submissionRepository.findById(submission.getId())
                .orElseThrow(() -> new ResourceNotFound("Submission not found with ID: " + submission.getId()));

        existingSubmission.setAssignmentId(submission.getAssignmentId());
        existingSubmission.setStudentId(submission.getStudentId());
        existingSubmission.setSubmissionTime(submission.getSubmissionTime());
        existingSubmission.setFile(submission.getFile());

        return submissionRepository.save(existingSubmission);
    }


    @Override
    public List<Submission> getSubmissionsByAssignmentId(long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<Submission> getSubmissionsByStudentId(long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    @Override
    public Submission getSubmissionByAssignmentIdAndStudentId(String assignmentId, String studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }
}
