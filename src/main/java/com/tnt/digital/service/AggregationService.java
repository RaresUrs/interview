package com.tnt.digital.service;

import com.tnt.digital.domain.AggregationRequest;
import com.tnt.digital.domain.AggregationResponse;

import java.util.concurrent.CompletableFuture;

public interface AggregationService {

    CompletableFuture<AggregationResponse> aggregateResponse(AggregationRequest request);
}
