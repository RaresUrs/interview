package com.tnt.digital.validator;

import com.tnt.digital.exception.OrderNumberException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderNumberValidatorTest {

    private final OrderNumberValidator orderNumberValidator = new OrderNumberValidator();

    @Test
    void shouldThrowExceptionWhenLengthIsNotNine() {
        var invalidOrderNumberFormat = List.of("12");
        assertThrows(
                OrderNumberException.class,
                () -> orderNumberValidator.validateOrderNumbers(invalidOrderNumberFormat)
        );
    }

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(
                OrderNumberException.class,
                () -> orderNumberValidator.validateOrderNumbers(null)
        );
    }

    @Test
    void shouldThrowExceptionOnInvalidFormat() {
        var invalidFormat = List.of("AAAAAAAAA");
        assertThrows(
                OrderNumberException.class,
                () -> orderNumberValidator.validateOrderNumbers(invalidFormat)
        );
    }
}