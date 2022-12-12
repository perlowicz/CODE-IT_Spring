package com.example.codeit_db_com.arch.controllers;

import com.example.codeit_db_com.arch.entities.Course;
import com.example.codeit_db_com.arch.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if (isNotEmpty(allCourses) && allCourses.get().size() > 0){
            model.addAttribute("courses", allCourses.orElse(Collections.emptyList()));
        }
        return "pages/course/list";
    }

    @GetMapping("/courses/add")
    String addNewCourse(Model model){
        model.addAttribute("course", new Course());
        return "pages/course/form";
    }

    @GetMapping("/courses/edit/{id}")
    String saveCourse(@PathVariable Long id, Model model){
        Optional<Course> courseFoundById = courseService.getCourseById(id);
        if (isNotEmpty(courseFoundById))
            model.addAttribute("course", courseFoundById.get());
        return "pages/course/form-edit";
    }

    @PostMapping("/courses")
    String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "pages/course/form";
        } else {
            courseService.saveCourse(course);
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/edit/{id}")
    String updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "pages/course/form-edit";
        } else {
            courseService.replaceCourse(course);
            return "redirect:/courses";
        }
    }

    @GetMapping("/courses/delete/{id}")
    String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    private boolean isNotEmpty(Optional<?> anySource){
        return anySource.isPresent();
    }
}
