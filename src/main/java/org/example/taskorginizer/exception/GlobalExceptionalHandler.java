package org.example.taskorginizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionalHandler {

        @ExceptionHandler(value = TaskIdNotFoundException.class)
        public ResponseEntity<ExceptionFormat> taskNotFound(TaskIdNotFoundException ex){
            ExceptionFormat exception = new ExceptionFormat(ex.getMessage(),HttpStatus.NOT_FOUND,new Date());
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = TaskNotFoundException.class)
        public ResponseEntity<ExceptionFormat> taskNotFound(TaskNotFoundException ex){
            ExceptionFormat exception = new ExceptionFormat(ex.getMessage(),HttpStatus.NOT_FOUND,new Date());
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<ExceptionFormat> globalException(UserAlreadyExistException ex){
            ExceptionFormat exception = new ExceptionFormat(ex.getMessage(), HttpStatus.CONFLICT,new Date());
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(value = UsernameNotFoundException.class)
        public ResponseEntity<ExceptionFormat> userNotFoundException(UsernameNotFoundException ex){
            ExceptionFormat exception = new ExceptionFormat(ex.getMessage(), HttpStatus.NOT_FOUND,new Date());
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

}
