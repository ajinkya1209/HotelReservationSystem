package com.project.springboothotelproject.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.PaymentMode;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class PaymentModeValidatorTest {

    @Test
    void testInitialize() {
        PaymentModeValidator paymentModeValidator = new PaymentModeValidator();
        paymentModeValidator.initialize(mock(ValidPaymentMode.class));
        assertTrue(paymentModeValidator.isValid(PaymentMode.CREDIT_CARD, null));
    }

    @Test
    void testIsValid() {
        PaymentModeValidator paymentModeValidator = new PaymentModeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(paymentModeValidator.isValid(PaymentMode.CREDIT_CARD,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    @Test
    void testIsValid2() {
        PaymentModeValidator paymentModeValidator = new PaymentModeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(paymentModeValidator.isValid(PaymentMode.DEBIT_CARD,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

