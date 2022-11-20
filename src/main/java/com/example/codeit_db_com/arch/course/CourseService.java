package com.example.codeit_db_com.arch.course;

import org.springframework.stereotype.Service;

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
}
