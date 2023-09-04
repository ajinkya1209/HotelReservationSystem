package com.project.springboothotelproject.exceptionhandling;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

// Custom annotation for validating Gender enum values
@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {

    // Default error message for invalid gender
    String message() default "Invalid gender. Supported types: MALE, FEMALE";

    // Groups for validation (default empty)
    Class<?>[] groups() default {};

    // Payload for validation (default empty)
    Class<? extends Payload>[] payload() default {};
}
