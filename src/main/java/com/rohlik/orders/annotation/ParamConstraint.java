package com.rohlik.orders.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rohlik.orders.validator.ParamValidator;

@Documented
@Constraint(validatedBy = ParamValidator.class)
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamConstraint
{
    String message() default "Id doesn't exist";

    ERepo repo();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
