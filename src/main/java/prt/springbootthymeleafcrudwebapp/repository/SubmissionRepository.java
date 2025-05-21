package prt.springbootthymeleafcrudwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prt.springbootthymeleafcrudwebapp.model.Submission;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByAssignmentId(long assignmentId);

    List<Submission> findByStudentId(long studentId);

    Submission findByAssignmentIdAndStudentId(String assignmentId, String studentId);
}
