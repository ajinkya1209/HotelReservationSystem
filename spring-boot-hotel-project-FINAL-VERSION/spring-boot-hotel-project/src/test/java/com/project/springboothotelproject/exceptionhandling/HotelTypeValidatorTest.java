package com.project.springboothotelproject.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.HotelType;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class HotelTypeValidatorTest {

    @Test
    void testInitialize() {
        HotelTypeValidator hotelTypeValidator = new HotelTypeValidator();
        hotelTypeValidator.initialize(mock(ValidHotelType.class));
        assertTrue(hotelTypeValidator.isValid(HotelType.TWO_STAR, null));
    }


    @Test
    void testIsValid() {
        HotelTypeValidator hotelTypeValidator = new HotelTypeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(hotelTypeValidator.isValid(HotelType.TWO_STAR,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }


    @Test
    void testIsValid2() {
        HotelTypeValidator hotelTypeValidator = new HotelTypeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(hotelTypeValidator.isValid(HotelType.THREE_STAR,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

