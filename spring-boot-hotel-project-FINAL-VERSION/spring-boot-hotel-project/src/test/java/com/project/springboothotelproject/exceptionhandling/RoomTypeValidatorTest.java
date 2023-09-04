package com.project.springboothotelproject.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.RoomType;
import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class RoomTypeValidatorTest {

    @Test
    void testInitialize() {
        RoomTypeValidator roomTypeValidator = new RoomTypeValidator();
        roomTypeValidator.initialize(mock(ValidRoomType.class));
        assertTrue(roomTypeValidator.isValid(RoomType.DELUXE, null));
    }

    @Test
    void testIsValid() {
        RoomTypeValidator roomTypeValidator = new RoomTypeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(roomTypeValidator.isValid(RoomType.DELUXE,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    @Test
    void testIsValid2() {
        RoomTypeValidator roomTypeValidator = new RoomTypeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(roomTypeValidator.isValid(RoomType.SUITE,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

