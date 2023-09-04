package com.project.springboothotelproject.exceptionhandling;

import com.project.springboothotelproject.enitites.PaymentMode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

// Custom validator for checking the validity of PaymentMode enum values
public class PaymentModeValidator implements ConstraintValidator<ValidPaymentMode, PaymentMode> {

    // Initialization method for the validator
    @Override
    public void initialize(ValidPaymentMode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // Validation logic to determine if a PaymentMode value is valid
    @Override
    public boolean isValid(PaymentMode value, ConstraintValidatorContext context) {
        // Check if the provided value is present in the PaymentMode enum
        return Arrays.asList(PaymentMode.values()).contains(value);
    }
}
