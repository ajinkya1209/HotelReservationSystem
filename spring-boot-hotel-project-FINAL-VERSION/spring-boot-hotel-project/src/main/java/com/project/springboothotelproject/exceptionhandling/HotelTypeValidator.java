package com.project.springboothotelproject.exceptionhandling;

import com.project.springboothotelproject.enitites.HotelType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

// Custom validator for checking the validity of HotelType enum values
public class HotelTypeValidator implements ConstraintValidator<ValidHotelType, HotelType> {

    // Initialization method for the validator
    @Override
    public void initialize(ValidHotelType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // Validation logic to determine if a HotelType value is valid
    @Override
    public boolean isValid(HotelType value, ConstraintValidatorContext context) {
        // Check if the provided value is present in the HotelType enum
        return Arrays.asList(HotelType.values()).contains(value);
    }
}
