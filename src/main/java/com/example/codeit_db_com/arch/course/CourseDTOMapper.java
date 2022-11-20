package com.example.codeit_db_com.arch.course;

import org.springframework.stereotype.Service;

@Service
public class CourseDTOMapper {

    public CourseDTO map(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setPrice(course.getPrice());
        return courseDTO;
    }
}
