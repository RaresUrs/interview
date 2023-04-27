package com.tnt.digital.validator;

import com.tnt.digital.exception.InvalidCountryCodeException;
import com.tnt.digital.exception.OrderNumberException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class OrderNumberValidator {

    public static final String REGEX = "\\d{9}";

    public void validateOrderNumbers(List<String> orderNumbers) {
        if (isEmpty(orderNumbers)) {
            throw new OrderNumberException("Order numbers cannot be null or empty");
        }
        orderNumbers.forEach(this::validateOrderNumber);
    }

    private void validateOrderNumber(String orderNumber) throws InvalidCountryCodeException {
        if (isEmpty(orderNumber)) {
            throw new OrderNumberException("Order numbers cannot be null or empty");
        }

        if (orderNumber.length() != 9) {
            throw new OrderNumberException("Invalid Order numbers length, length should be 9: " + orderNumber);
        }

        if (!orderNumber.matches(REGEX)) {
            throw new OrderNumberException("Invalid order format: " + orderNumber);
        }
    }
}
