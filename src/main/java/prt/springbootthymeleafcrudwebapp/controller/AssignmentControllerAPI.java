package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Assignment;
import prt.springbootthymeleafcrudwebapp.model.Staff;
import prt.springbootthymeleafcrudwebapp.model.User;
import prt.springbootthymeleafcrudwebapp.service.AssignmentService;
import prt.springbootthymeleafcrudwebapp.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentControllerAPI {

    @Autowired
    private AssignmentService assignmentService;
    private StaffService staffService;

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourseID(@PathVariable("courseId") long courseId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByCourseID(courseId);
        if (assignments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<?> getStaffById(@PathVariable("staffId") Long staffId) {
        try {
            Staff staff = staffService.getStaffById(staffId);

            if (staff == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Staff member not found with id: " + staffId);
            }

            String staffFullName = staff.getFullName();

            if (staffFullName == null || staffFullName.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Full name for the staff member is unavailable.");
            }

            return ResponseEntity.ok(staffFullName);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching staff member: " + e.getMessage());
        }
    }


    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignmentService.saveAssignment(assignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignmentDetails) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        assignment.setTitle(assignmentDetails.getTitle());
        assignment.setDescription(assignmentDetails.getDescription());
        assignment.setDueDate(assignmentDetails.getDueDate());
        assignment.setCourseId(assignmentDetails.getCourseId());
        assignment.setStaffId(assignmentDetails.getStaffId());
        Assignment updatedAssignment = assignmentService.updateAssignment(assignment);
        return ResponseEntity.ok(updatedAssignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }
}
