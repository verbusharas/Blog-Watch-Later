package lt.verbus.svblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {


    @RequestMapping("/all")
    public String renderAllPosts(){
        return "posts";
    }

}
