package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.User;
import lt.verbus.svblog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController extends DefaultController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getUserRegistrationForm(Model model){
        model.addAttribute("newUser", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("newUser") @Valid User newUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        userService.save(newUser);
        return "redirect:/private/user";
    }



}
