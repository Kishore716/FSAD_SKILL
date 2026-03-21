package klu.Skill9.exception;

import klu.Skill9.excerption.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFound(StudentNotFoundException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(InvalidInputException.class)
    public ErrorResponse handleInvalidInput(InvalidInputException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }
}