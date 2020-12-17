package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.Comment;
import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.service.CommentService;
import lt.verbus.svblog.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/post")
@SessionAttributes({"post"})
public class PostController extends DefaultController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    @GetMapping("/{id}")
    public String getSinglePost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("otherPosts", postService.getAllExcept(id));
        return "post";
    }


    @GetMapping("/{id}/comment")
    public String getSinglePostComments(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("otherPosts", postService.getAllExcept(id));
        model.addAttribute("comment", new Comment());
        return "post-comment";
    }

    @PostMapping("/comment")
    public String createComment(@ModelAttribute Comment comment,
                                @ModelAttribute("post") Post post,
                                @AuthenticationPrincipal User user, Model model) {
        comment.setPost(post);
        comment.setUser(user);
        commentService.save(comment);
        return "redirect:/post/" + comment.getPost().getId() + "/comment";
    }

}
