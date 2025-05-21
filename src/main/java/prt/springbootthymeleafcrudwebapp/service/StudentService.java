package prt.springbootthymeleafcrudwebapp.service;

import prt.springbootthymeleafcrudwebapp.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(long id);
    void saveStudent(Student student);
    void deleteStudentById(long id);
    Student getStudentByStudentId(String studentId);
}