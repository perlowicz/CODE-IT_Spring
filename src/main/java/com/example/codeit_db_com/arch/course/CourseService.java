package com.example.codeit_db_com.arch.course;

import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseDTOMapper courseDTOMapper;
    private final CourseRepository courseRepository;
    private final SimpleCourseDTOMapper simpleCourseDTOMapper;

    public CourseService(CourseDTOMapper courseDTOMapper,
                         CourseRepository courseRepository,
                         SimpleCourseDTOMapper simpleCourseDTOMapper) {
        this.courseDTOMapper = courseDTOMapper;
        this.courseRepository = courseRepository;
        this.simpleCourseDTOMapper = simpleCourseDTOMapper;
    }

    Optional<CourseDTO> getCourseById(Long id){
        return courseRepository.findById(id).map(courseDTOMapper::map);
    }

    Optional<List<SimpleCourseDTO>> getAllCourses(){
        List<SimpleCourseDTO> resultList = new ArrayList<>();
        courseRepository.findAll().forEach(course -> {
            SimpleCourseDTO simpleCourseDTO = simpleCourseDTOMapper.map(course);
            resultList.add(simpleCourseDTO);
        });
        return Optional.of(resultList);
    }

    SimpleCourseDTO saveCourse(SimpleCourseDTO simpleCourseDTO){
        Course map = simpleCourseDTOMapper.map(simpleCourseDTO);
        Course savedClient = courseRepository.save(map);
        return simpleCourseDTOMapper.map(savedClient);
    }
}
