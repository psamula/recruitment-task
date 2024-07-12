package com.psamula.recruitment_task.exception.handler;

import com.psamula.recruitment_task.exception.InvalidInputException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> handleInvalidInputException(InvalidInputException ex) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Setter
    private static class ErrorDetails {
        private HttpStatus status;
        private String message;

        public ErrorDetails(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
