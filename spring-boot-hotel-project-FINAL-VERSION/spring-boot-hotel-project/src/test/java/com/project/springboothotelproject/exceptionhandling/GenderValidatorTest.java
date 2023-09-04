package com.project.springboothotelproject.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.Gender;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class GenderValidatorTest {

    @Test
    void testInitialize() {
        GenderValidator genderValidator = new GenderValidator();
        genderValidator.initialize(mock(ValidGender.class));
        assertTrue(genderValidator.isValid(Gender.MALE, null));
    }


    @Test
    void testIsValid() {
        GenderValidator genderValidator = new GenderValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(genderValidator.isValid(Gender.MALE,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }


    @Test
    void testIsValid2() {
        GenderValidator genderValidator = new GenderValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(genderValidator.isValid(Gender.FEMALE,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

