package lt.verbus.svblog.controller;

import lt.verbus.svblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class IndexController extends DefaultController {

    private final PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String renderHome(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

}
