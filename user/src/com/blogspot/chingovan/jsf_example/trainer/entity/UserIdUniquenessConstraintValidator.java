package com.blogspot.chingovan.jsf_example.trainer.entity;

import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ChiNV on 11/27/2017.
 */
public abstract class UserIdUniquenessConstraintValidator implements ConstraintValidator<UserIdUniquenessConstraint, String> {

    public boolean isValid(Long value, ConstraintValidatorContext ctx){
        return (null == UserRegistry.getInstance().
                getUserByUserId(value));
    }
    public void initialize(UserIdUniquenessConstraint userIdUniquenessConstraint) { }
}
