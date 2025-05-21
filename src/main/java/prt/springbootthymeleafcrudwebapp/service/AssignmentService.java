package prt.springbootthymeleafcrudwebapp.service;

import prt.springbootthymeleafcrudwebapp.model.Assignment;
import java.util.List;

public interface AssignmentService {

    List<Assignment> getAllAssignments();

    List<Assignment> getAssignmentsByCourseID(long courseId);

    Assignment getAssignmentById(long id);

    Assignment saveAssignment(Assignment assignment);

    void deleteAssignmentById(long id);

    Assignment updateAssignment(Assignment assignment);

    List<Assignment> getAssignmentsByCourseId(long courseId);

    List<Assignment> getAssignmentsByStaffId(int staffId);
}