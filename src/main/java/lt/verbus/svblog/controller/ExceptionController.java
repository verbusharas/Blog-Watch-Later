package lt.verbus.svblog.controller;

import lt.verbus.svblog.exception.CommentNotFoundException;
import lt.verbus.svblog.exception.PostNotFoundException;
import lt.verbus.svblog.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<Object> postException(PostNotFoundException exception) {
        return new ResponseEntity<>("Postas nerastas", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<Object> commentException(CommentNotFoundException exception) {
        return new ResponseEntity<>("Komentaras nerastas", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userException(UserNotFoundException exception) {
        return new ResponseEntity<>("Vartotojas nerastas", HttpStatus.NOT_FOUND);
    }

}
