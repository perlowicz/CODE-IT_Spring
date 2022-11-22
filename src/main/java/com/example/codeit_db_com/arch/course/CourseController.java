package com.example.codeit_db_com.arch.course;

import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/{id}")
    ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/courses")
    ResponseEntity<List<SimpleCourseDTO>> getAllCourses(){
        return courseService.getAllCourses()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/courses")
    ResponseEntity<SimpleCourseDTO> save(@RequestBody SimpleCourseDTO simpleCourseDTO){
        SimpleCourseDTO saveCourse = courseService.saveCourse(simpleCourseDTO);
        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveCourse.getId())
                .toUri();
        return ResponseEntity.created(savedClientURI).body(saveCourse);
    }
}
