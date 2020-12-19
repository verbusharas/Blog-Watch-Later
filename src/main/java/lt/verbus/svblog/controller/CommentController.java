package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.Comment;
import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.service.CommentService;
import lt.verbus.svblog.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/post/comment")
@SessionAttributes({"post"})
public class CommentController extends DefaultController {

    private final PostService postService;
    private final CommentService commentService;

    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    @GetMapping("/{postId}")
    public String composeComment(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostById(postId));
        model.addAttribute("otherPosts", postService.getAllExcept(postId));
        model.addAttribute("newComment", new Comment());
        return "post/new-comment";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/")
    public String publishComment(@ModelAttribute("newComment") @Valid Comment comment,
                                 BindingResult bindingResult,
                                 @ModelAttribute("post") Post post,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        if(bindingResult.hasErrors()){
            return "post/new-comment";
        }
        comment.setPost(post);
        comment.setUser(user);
        commentService.save(comment);
        return "redirect:/post/comment/" + post.getId();
    }

    @PreAuthorize("#user.username == authentication.principal.username or hasRole('ADMIN')")
    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId,
                                @ModelAttribute("post") Post post,
                                @AuthenticationPrincipal User user,
                                Model model) {
        commentService.deleteComment(commentService.getCommentById(commentId));
        return "redirect:/post/comment/" + post.getId();
    }

    @PreAuthorize("#user.username == authentication.principal.username")
    @GetMapping("/edit/{commentId}")
    public String makeCommentEditable(@PathVariable Long commentId,
                                      @ModelAttribute("post") Post post,
                                      @AuthenticationPrincipal User user,
                                      Model model) {
        model.addAttribute("otherPosts", postService.getAllExcept(post.getId()));
        model.addAttribute("editableComment", commentService.getCommentById(commentId));
        return "post/edit-comment";
    }

    @PostMapping("/edit")
    public String updateComment(@ModelAttribute("editableComment") @Valid Comment editableComment,
                                BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("editableComment", commentService.getCommentById(editableComment.getId()));
            return "post/edit-comment";
        }
        Comment comment = commentService.update(editableComment);
        return "redirect:/post/comment/" + comment.getPost().getId();
    }

}
