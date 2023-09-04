package com.project.springboothotelproject.exceptionhandling;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// Custom annotation for validating Payment Mode enum values
@Documented
@Constraint(validatedBy = PaymentModeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPaymentMode {

    // Default error message for invalid payment mode
    String message() default "Invalid payment mode. Supported mode: CREDIT_CARD,DEBIT_CARD,UPI,NET_BANKING";

    // Groups for validation (default empty)
    Class<?>[] groups() default {};

    // Payload for validation (default empty)
    Class<? extends Payload>[] payload() default {};
}
