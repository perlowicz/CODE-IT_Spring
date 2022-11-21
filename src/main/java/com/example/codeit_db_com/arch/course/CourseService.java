package com.example.codeit_db_com.arch.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseDTOMapper courseDTOMapper;
    private final CourseRepository courseRepository;

    public CourseService(CourseDTOMapper courseDTOMapper, CourseRepository courseRepository) {
        this.courseDTOMapper = courseDTOMapper;
        this.courseRepository = courseRepository;
    }

    Optional<CourseDTO> getCourseById(Long id){
        return courseRepository.findById(id).map(courseDTOMapper::map);
    }

    Optional<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> resultList = new ArrayList<>();
        courseRepository.findAll().iterator().forEachRemaining(course -> {
            CourseDTO mappedCourse = courseDTOMapper.map(course);
            resultList.add(mappedCourse);
        });
        return Optional.of(resultList);
    }
}
