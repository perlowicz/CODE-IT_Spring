package com.example.codeit_db_com.arch.course;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    String getAllCourses(Model model){
        Optional<List<Course>> allCourses = courseService.getAllCourses();
        if (allCourses.isPresent() && allCourses.get().size() > 0){
            model.addAttribute("courses", allCourses.orElse(Collections.emptyList()));
        }
        return "pages/course/list";
    }

    @GetMapping("/courses/add") //redirect from list to form
    String addNewCourse(){
        return "pages/course/form";
    }

    @GetMapping("/courses/edit/{id}")
    String saveCourse(@PathVariable Long id, Model model){
        Optional<Course> courseById = courseService.getCourseById(id);
        if (courseById.isPresent())
            model.addAttribute("course", courseById.get());
        return "pages/course/form-edit";
    }

    @PostMapping("/courses")
    String saveCourse(Course course){
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/courses/edit/{id}")
    String updateCourse(Course course){
        courseService.replaceCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
    //    @GetMapping("/courses")
//    ResponseEntity<List<SimpleCourseDTO>> getAllCourses(){
//        return courseService.getAllCourses()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    //    @PostMapping("/courses")
//    ResponseEntity<SimpleCourseDTO> save(@RequestBody SimpleCourseDTO simpleCourseDTO){
//        SimpleCourseDTO saveCourse = courseService.saveCourse(simpleCourseDTO);
//        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saveCourse.getId())
//                .toUri();
//        return ResponseEntity.created(savedClientURI).body(saveCourse);
//    }
}
