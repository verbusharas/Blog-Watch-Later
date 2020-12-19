package lt.verbus.svblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPagesController {

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/sign-in-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user/login";
    }


}
