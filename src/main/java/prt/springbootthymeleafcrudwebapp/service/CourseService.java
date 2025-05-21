package prt.springbootthymeleafcrudwebapp.service;

import prt.springbootthymeleafcrudwebapp.model.Course;
import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(long id);

    Course saveCourse(Course course);

    void deleteCourseById(long id);

    Course updateCourse(Course course);

    List<Course> getCoursesByStaffId(int staffId);
}
