package lt.verbus.svblog.controller;


import lt.verbus.svblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private/user")

public class UserControllerPrivate extends DefaultController {

    private final UserService userService;

    public UserControllerPrivate(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

}
