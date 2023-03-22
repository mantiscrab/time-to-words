package com.example.timetowords;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
class TimeToWordsControllerAdvice {
    @ExceptionHandler(TimeMustNotBeNullException.class)
    ResponseEntity<String> handleTimeMustNotBeNullException(TimeMustNotBeNullException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }
}
