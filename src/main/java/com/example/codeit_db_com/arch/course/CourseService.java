package com.example.codeit_db_com.arch.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Optional<List<Course>> getAllCourses(){
        List<Course> resultList = new ArrayList<>();
        courseRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    Course saveCourse(Course course){
        Course savedClient = courseRepository.save(course);
        return savedClient;
    }

    Optional<Course> replaceCourse(Long courseId, Course course){
        if (!courseRepository.existsById(courseId))
            return Optional.empty();
        course.setId(courseId);
        Course updatedCourse = courseRepository.save(course);
        return Optional.of(updatedCourse);
    }

    void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
