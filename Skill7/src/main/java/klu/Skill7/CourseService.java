package klu.Skill7;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private Map<Integer, Course> courseMap = new HashMap<>();

    public void addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    public Course getCourseById(int id) {
        return courseMap.get(id);
    }

    public void updateCourse(int id, Course course) {
        courseMap.put(id, course);
    }

    public void deleteCourse(int id) {
        courseMap.remove(id);
    }

    public List<Course> searchByTitle(String title) {

        List<Course> list = new ArrayList<>();

        for(Course c : courseMap.values()) {
            if(c.getTitle().equalsIgnoreCase(title)) {
                list.add(c);
            }
        }

        return list;
    }
}