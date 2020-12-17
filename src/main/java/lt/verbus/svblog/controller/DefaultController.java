package lt.verbus.svblog.controller;

import lt.verbus.svblog.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user"})
public class DefaultController {

    @ModelAttribute("user")
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

}
