package com.project.springboothotelproject.exceptionhandling;

import com.project.springboothotelproject.enitites.RoomType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

// Custom validator for checking the validity of RoomType enum values
public class RoomTypeValidator implements ConstraintValidator<ValidRoomType, RoomType> {

    // Initialization method for the validator
    @Override
    public void initialize(ValidRoomType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // Validation logic to determine if a RoomType value is valid
    @Override
    public boolean isValid(RoomType value, ConstraintValidatorContext context) {
        // Check if the provided value is present in the RoomType enum
        return Arrays.asList(RoomType.values()).contains(value);
    }
}
