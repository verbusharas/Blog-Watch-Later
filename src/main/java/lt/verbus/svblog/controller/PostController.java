package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
@SessionAttributes({"post"})
public class PostController extends DefaultController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String goHome() {
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("otherPosts", postService.getAllExcept(id));
        return "post/view";
    }

    @GetMapping("/filter")
    public String filterByType(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("posts", postService.getAllContainingType(type));
        return "post/filtered-feed";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/compose")
    public String composePost(Model model) {
        model.addAttribute("newPost", new Post());
        model.addAttribute("posts", postService.getAll());
        return "post/compose";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/compose")
    public String publishPost(@ModelAttribute("newPost") @Valid Post newPost,
                          BindingResult bindingResult,
                              Model model) {
        if(bindingResult.hasErrors()){
            return "post/compose";
        }
        postService.save(newPost);
        return "redirect:/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String makePostEditable(@PathVariable Long id, Model model) {
        model.addAttribute("editablePost", postService.getPostById(id));
        return "post/edit";
    }

    @PostMapping("/edit")
    public String updatePost(@ModelAttribute("editablePost") @Valid Post editedPost,
                                   BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "post/edit";
        }
        postService.update(editedPost);
        return "redirect:/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, Model model) {
        postService.deleteById(id);
        return "redirect:/index";
    }

}
