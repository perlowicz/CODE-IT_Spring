package com.example.codeit_db_com.arch.repositories;

import com.example.codeit_db_com.arch.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findCourseByName(String name);
}
