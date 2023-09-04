package com.project.springboothotelproject.exceptionhandling;

import com.project.springboothotelproject.enitites.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Custom validator for checking the validity of Gender enum values
public class GenderValidator implements ConstraintValidator<ValidGender, Gender> {

    // Initialization method for the validator
    @Override
    public void initialize(ValidGender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // Validation logic to determine if a Gender value is valid
    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        return value == Gender.MALE || value == Gender.FEMALE;
    }
}
