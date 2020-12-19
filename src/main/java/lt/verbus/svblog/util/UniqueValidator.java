package lt.verbus.svblog.util;

import lt.verbus.svblog.annotation.Unique;
import lt.verbus.svblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userService != null) {
            return userService.isUsernameUnique(username);
        }
        return true;
    }
}
