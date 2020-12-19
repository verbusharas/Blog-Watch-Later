package lt.verbus.svblog.annotation;

import lt.verbus.svblog.util.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueValidator.class})
public @interface Unique {
    String message() default "{user.username.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
