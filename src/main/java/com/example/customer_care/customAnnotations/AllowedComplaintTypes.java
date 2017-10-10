package com.example.customer_care.customAnnotations;

import com.example.customer_care.entity.StringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedComplaintTypes {


    String[] acceptedValues();

    String message() default "Provide within allowed types only;";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
