package prt.springbootthymeleafcrudwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prt.springbootthymeleafcrudwebapp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentId(String studentId);
}
