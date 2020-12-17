package lt.verbus.svblog.controller;

import lt.verbus.svblog.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostExceptionController {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<Object> exception(PostNotFoundException exception) {
        return new ResponseEntity<>("Postas nerastas", HttpStatus.NOT_FOUND);
    }

}
