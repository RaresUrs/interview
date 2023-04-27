package com.tnt.digital.client;

import com.tnt.digital.domain.PricingResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PriceClient {
    CompletableFuture<PricingResponse> getPrice(List<String> countryCode);
}
