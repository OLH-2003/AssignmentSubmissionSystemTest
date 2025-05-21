package prt.springbootthymeleafcrudwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prt.springbootthymeleafcrudwebapp.model.Assignment;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByCourseId(long courseId);

    List<Assignment> findByStaffId(int staffId);
}
