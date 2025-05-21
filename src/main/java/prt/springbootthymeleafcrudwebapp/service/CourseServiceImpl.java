package prt.springbootthymeleafcrudwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.springbootthymeleafcrudwebapp.model.Course;
import prt.springbootthymeleafcrudwebapp.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found for id: " + id));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Optional<Course> existingCourseOptional = courseRepository.findById(course.getId());

        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            existingCourse.setName(course.getName());
            existingCourse.setStaffId(course.getStaffId());

            return courseRepository.save(existingCourse);
        } else {
            throw new RuntimeException("Course not found with id: " + course.getId());
        }
    }

    @Override
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesByStaffId(int staffId) {
        return courseRepository.findByStaffId(staffId);
    }
}