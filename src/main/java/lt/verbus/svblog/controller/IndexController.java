package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class IndexController extends DefaultController {

    private final PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String renderHome(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/compose")
    public String compose(Model model) {
        model.addAttribute("post", new Post());
        return "compose";
    }

    @PostMapping("/compose")
    public String publish(@ModelAttribute("post") Post post, Model model) {
        postService.save(post);
        return "redirect:/index";
    }


}
