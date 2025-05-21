package prt.springbootthymeleafcrudwebapp.service;

import prt.springbootthymeleafcrudwebapp.model.Submission;
import java.util.List;

public interface SubmissionService {

    List<Submission> getAllSubmissions();

    Submission getSubmissionById(long id);

    Submission saveSubmission(Submission submission);

    void deleteSubmissionById(long id);

    Submission updateSubmission(Submission submission);

    List<Submission> getSubmissionsByAssignmentId(long assignmentId);

    List<Submission> getSubmissionsByStudentId(long studentId);

    Submission getSubmissionByAssignmentIdAndStudentId(String assignmentId, String studentId);
}