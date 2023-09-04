package com.project.springboothotelproject.exceptionhandling;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// Custom annotation for validating Room Type enum values
@Documented
@Constraint(validatedBy = RoomTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoomType {

    // Default error message for invalid room type
    String message() default "Invalid room type. Supported types:  DELUXE, SUITE, DOUBLE_ROOM;";

    // Groups for validation (default empty)
    Class<?>[] groups() default {};

    // Payload for validation (default empty)
    Class<? extends Payload>[] payload() default {};
}
