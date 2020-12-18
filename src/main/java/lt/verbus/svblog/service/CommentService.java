package lt.verbus.svblog.service;

import lt.verbus.svblog.exception.CommentNotFoundException;
import lt.verbus.svblog.model.Comment;
import lt.verbus.svblog.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

}
