package com.tnt.digital.utils;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.util.Objects.isNull;

public class Utils {

    private static final String QUERY = "q=";
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    public static String getQueryParams(List<String> countryCodes) {
        var sb = new StringBuilder();
        sb.append(QUERY);
        for (var code : countryCodes) {
            sb.append(code);
            sb.append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public static boolean isValidISOCountry(String s) {
        if (isNull(s)) return false;
        return ISO_COUNTRIES.contains(s);
    }
}
