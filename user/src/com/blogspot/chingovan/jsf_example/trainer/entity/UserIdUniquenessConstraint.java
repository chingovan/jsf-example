package com.blogspot.chingovan.jsf_example.trainer.entity;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by ChiNV on 11/27/2017.
 */
@Documented
@Constraint(validatedBy = UserIdUniquenessConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIdUniquenessConstraint {
    String message() default "A user with that userid already exists";
    Class<?>[] groups() default {};
}
