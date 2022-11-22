package com.example.codeit_db_com.arch.course;

import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SimpleCourseDTOMapper {

    public SimpleCourseDTO map(Course course){
        SimpleCourseDTO simpleCourseDTO = new SimpleCourseDTO();

        simpleCourseDTO.setId(course.getId());
        simpleCourseDTO.setName(course.getName());
        simpleCourseDTO.setDescription(course.getDescription());
        simpleCourseDTO.setPrice(course.getPrice());

        return simpleCourseDTO;
    }

    public Course map(SimpleCourseDTO simpleCourseDTO){
        Course course = new Course();

        course.setTransactions(Collections.emptyList());
        course.setId(simpleCourseDTO.getId());
        course.setName(simpleCourseDTO.getName());
        course.setDescription(simpleCourseDTO.getDescription());
        course.setPrice(simpleCourseDTO.getPrice());

        return course;
    }
}
