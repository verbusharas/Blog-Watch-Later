package lt.verbus.svblog.service;

import lt.verbus.svblog.model.Comment;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.repository.CommentRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

}
