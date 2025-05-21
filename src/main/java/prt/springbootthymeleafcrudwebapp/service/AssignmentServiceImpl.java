package prt.springbootthymeleafcrudwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.springbootthymeleafcrudwebapp.model.Assignment;
import prt.springbootthymeleafcrudwebapp.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment getAssignmentById(long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found for id: " + id));
    }

    @Override
    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAssignmentsByCourseID(long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }


    @Override
    public Assignment updateAssignment(Assignment updatedAssignment) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(updatedAssignment.getId());

        if (optionalAssignment.isPresent()) {
            Assignment existingAssignment = optionalAssignment.get();

            existingAssignment.setTitle(updatedAssignment.getTitle());
            existingAssignment.setDescription(updatedAssignment.getDescription());
            existingAssignment.setDueDate(updatedAssignment.getDueDate());
            existingAssignment.setCourseId(updatedAssignment.getCourseId());
            existingAssignment.setStaffId(updatedAssignment.getStaffId());

            return assignmentRepository.save(existingAssignment);
        } else {
            throw new RuntimeException("Assignment not found with id: " + updatedAssignment.getId());
        }
    }

    @Override
    public void deleteAssignmentById(long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    @Override
    public List<Assignment> getAssignmentsByStaffId(int staffId) {
        return assignmentRepository.findByStaffId(staffId);
    }
}