package com.example.codeit_db_com.arch.course;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/{id}")
    ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/courses")
//    ResponseEntity<List<SimpleCourseDTO>> getAllCourses(){
//        return courseService.getAllCourses()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/courses")
    String getAllCourses(Model model){
        Optional<List<Course>> allCourses = courseService.getAllCourses();
        if (allCourses.isPresent() && allCourses.get().size() > 0){
            model.addAttribute("courses", allCourses.get());
        }
        return "pages/course/list";
    }

    @GetMapping("/courses/add") //redirect from list to form
    String addNewCourse(){
        return "pages/course/form";
    }

    @PostMapping("/courses")
    String saveCourse(Course course){
        courseService.saveCourse(course);
        return "redirect:courses";
    }

//    @PostMapping("/courses")
//    ResponseEntity<SimpleCourseDTO> save(@RequestBody SimpleCourseDTO simpleCourseDTO){
//        SimpleCourseDTO saveCourse = courseService.saveCourse(simpleCourseDTO);
//        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saveCourse.getId())
//                .toUri();
//        return ResponseEntity.created(savedClientURI).body(saveCourse);
//    }

    @PutMapping("/courses/{id}")
    ResponseEntity<?> update(@PathVariable Long id,
                             @RequestBody Course course){
        return courseService.replaceCourse(id, course)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/courses/{id}")
    ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
