package com.tnt.digital.validator;

import com.tnt.digital.exception.InvalidCountryCodeException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tnt.digital.utils.Utils.isValidISOCountry;

@Component
public class CountryCodeValidator {

    public void validateCountryCodes(List<String> countryCodes) {
        countryCodes.forEach(this::validateCountryCode);
    }

    private void validateCountryCode(String countryCode) throws InvalidCountryCodeException {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new InvalidCountryCodeException("Country code cannot be null or empty");
        }
        if (countryCode.length() != 2) {
            throw new InvalidCountryCodeException("Invalid country code length: " + countryCode);
        }
        if (!isValidISOCountry(countryCode)) {
            throw new InvalidCountryCodeException("Invalid country ISO code, it does not exist: " + countryCode);
        }
    }
}
