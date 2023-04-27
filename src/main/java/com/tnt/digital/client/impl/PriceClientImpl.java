package com.tnt.digital.client.impl;

import com.tnt.digital.client.PriceClient;
import com.tnt.digital.domain.PricingResponse;
import com.tnt.digital.validator.CountryCodeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.tnt.digital.utils.Utils.getQueryParams;

@Service
@RequiredArgsConstructor
public class PriceClientImpl implements PriceClient {
    private static final String PRICE_ENDPOINT = "/pricing";
    private static final String PRICING_SERVICE_ERROR_MESSAGE = "Pricing Service Unavailable";
    private final WebClient webClient;
    private final CountryCodeValidator validator;

    @Override
    public CompletableFuture<PricingResponse> getPrice(List<String> countryCodes) {
        validator.validateCountryCodes(countryCodes);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PRICE_ENDPOINT)
                        .query(getQueryParams(countryCodes))
                        .build())
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.SERVICE_UNAVAILABLE),
                        response -> Mono.error(new ServiceUnavailableException(PRICING_SERVICE_ERROR_MESSAGE)))
                .bodyToMono(Map.class)
                .map(this::buildResponse)
                .toFuture();
    }

    private PricingResponse buildResponse(Map<String, Double> map) {
        return PricingResponse.builder()
                .price(map)
                .build();
    }
}
