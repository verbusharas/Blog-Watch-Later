package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.Comment;
import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.service.CommentService;
import lt.verbus.svblog.service.PostService;
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
import java.util.Collections;
import java.util.List;

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

    @GetMapping
    public String goHome() {
        return "redirect:/";
    }

    @GetMapping("/compose")
    public String compose(Model model) {
        model.addAttribute("newPost", new Post());
        model.addAttribute("posts", postService.getAll());
        return "post/compose";
    }

    @PostMapping("/compose")
    public String publish(@ModelAttribute("newPost") @Valid Post newPost,
                          BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "post/compose";
        }
        postService.save(newPost);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit")
    public String makePostEditable(@PathVariable Long id, Model model) {
        model.addAttribute("editablePost", postService.getPostById(id));
        return "post/edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("editablePost") @Valid Post editedPost,
                                   BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "post/edit";
        }
        postService.update(editedPost);
        return "redirect:/index";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, Model model) {
        postService.deleteById(id);
        return "redirect:/index";
    }


    @GetMapping("/{id}")
    public String getSinglePost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("otherPosts", postService.getAllExcept(id));
        return "post/view";
    }

    @GetMapping("/{id}/comment")
    public String getSinglePostComments(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("otherPosts", postService.getAllExcept(id));
        model.addAttribute("newComment", new Comment());
        return "post/new-comment";
    }

    @PostMapping("/comment")
    public String createComment(@ModelAttribute Comment comment,
                                @ModelAttribute("post") Post post,
                                @AuthenticationPrincipal User user,
                                Model model) {
        comment.setPost(post);
        comment.setUser(user);
        post.getComments().add(comment);
        postService.save(post);
        return "redirect:/post/" + post.getId() + "/comment";
    }

    @GetMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId,
                                @ModelAttribute("post") Post post,
                                Model model) {
        commentService.deleteComment(commentService.getCommentById(commentId));
        return "redirect:/post/" + post.getId() + "/comment";
    }

    @GetMapping("/comment/{commentId}/edit")
    public String makeCommentEditable(@PathVariable Long commentId,
                                      @ModelAttribute("post") Post post,
                                      Model model) {
        model.addAttribute("otherPosts", postService.getAllExcept(post.getId()));
        model.addAttribute("editableComment", commentService.getCommentById(commentId));
        return "post/edit-comment";
    }

    @PostMapping("/comment/edit")
    public String updateComment(@ModelAttribute Comment editedComment, Model model) {
        Comment comment = commentService.update(editedComment);
        return "redirect:/post/" + comment.getPost().getId() + "/comment";
    }


}
