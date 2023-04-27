package com.tnt.digital.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    void getQueryParams() {
        var inputCountryCodes = List.of("GB", "FR");

        var queryString = Utils.getQueryParams(inputCountryCodes);

        assertThat(queryString).isEqualTo("q=GB,FR");
    }


    @Test
    void validIsoCountryCode() {
        var validCountryCode = "FR";

        var isValidIso = Utils.isValidISOCountry(validCountryCode);

        assertTrue(isValidIso);
    }

    @Test
    void invalidIsoCountryCode() {
        var invalidISOCountryCode = "ABSASD";

        var isValidIso = Utils.isValidISOCountry(invalidISOCountryCode);

        assertFalse(isValidIso);
    }

    @Test
    void isValidCountryISOCodeHandlesNulls() {
        var isValidIso = Utils.isValidISOCountry(null);

        assertFalse(isValidIso);
    }
}