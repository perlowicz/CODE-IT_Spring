package com.example.codeit_db_com.arch.service;

import com.example.codeit_db_com.arch.repositories.CourseRepository;
import com.example.codeit_db_com.arch.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Optional<List<Course>> getAllCourses(){
        List<Course> resultList = new ArrayList<>();
        courseRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public Optional<Course> replaceCourse(Course course){
        if (!courseRepository.existsById(course.getId()))
            return Optional.empty();
        Course updatedCourse = courseRepository.save(course);
        return Optional.of(updatedCourse);
    }

    public Optional<Course> getCourseByName(String name){
        return courseRepository.findCourseByName(name);
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    public List<String> mapCoursesToNames(){
        Optional<List<Course>> courses = getAllCourses();
        if (courses.isPresent())
            return courses.get().stream()
                    .map(Course::getName)
                    .collect(Collectors.toList());
        else
            return Collections.emptyList();
    }
}
