package klu.Skill7;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // Add Course
    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        service.addCourse(course);
        					return new ResponseEntity<>("Course Added", HttpStatus.CREATED);
    }

    // Display All Courses
    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> list = service.getAllCourses();
        						return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //  Course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Course c = service.getCourseById(id);
        					return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // Update Course
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course course) {
        service.updateCourse(id, course);
        		return new ResponseEntity<>("Course Updated", HttpStatus.OK);
    }

    // Delete Course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        service.deleteCourse(id);
        		return new ResponseEntity<>("Course Deleted", HttpStatus.OK);
    }

    // Search by Title
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {
        List<Course> list = service.searchByTitle(title);
        					return new ResponseEntity<>(list, HttpStatus.OK);
    }
}