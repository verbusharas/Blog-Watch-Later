package lt.verbus.svblog.controller;


import lt.verbus.svblog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/promote/{id}")
    public String promoteUser(@PathVariable Long id, Model model){
        userService.promoteUserById(id);
        return "redirect:/private/user";
    }

    @GetMapping("/demote/{id}")
    public String demoteUser(@PathVariable Long id, Model model){
        userService.demoteUserById(id);
        return "redirect:/private/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model){
        userService.deleteUserById(id);
        return "redirect:/private/user";
    }


}
