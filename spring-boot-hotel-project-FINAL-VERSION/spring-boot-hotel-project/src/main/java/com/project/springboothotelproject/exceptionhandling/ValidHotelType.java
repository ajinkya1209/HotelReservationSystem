package com.project.springboothotelproject.exceptionhandling;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// Custom annotation for validating Hotel Type enum values
@Documented
@Constraint(validatedBy = HotelTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHotelType {

    // Default error message for invalid Hotel Type
    String message() default "Invalid hotel type. Supported types: TWO_STAR, THREE_STAR, FOUR_STAR, FIVE_STAR";

    // Groups for validation (default empty)
    Class<?>[] groups() default {};

    // Payload for validation (default empty)
    Class<? extends Payload>[] payload() default {};
}
