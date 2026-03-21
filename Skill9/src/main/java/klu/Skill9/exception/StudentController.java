package klu.Skill9.exception;
import org.springframework.web.bind.annotation.*;
import klu.Skill9.exception.InvalidInputException;
import klu.Skill9.exception.StudentNotFoundException;
@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        int studentId;

        try {
            studentId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: ID must be a number");
        }
        if (studentId == 1) {
            return new Student(1, "Ravi");
        } else if (studentId == 2) {
            return new Student(2, "Sita");
        } else if (studentId == 3) {
            return new Student(3, "Rahul");
        } else {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
        }
    }
}