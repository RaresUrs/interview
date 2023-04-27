package com.tnt.digital.controller;

import com.tnt.digital.domain.AggregationRequest;
import com.tnt.digital.domain.AggregationResponse;
import com.tnt.digital.service.AggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;


@RequiredArgsConstructor
@RestController("/api")
public class AggregationController {

    private final AggregationService aggregationService;

    @GetMapping("/aggregation")
    public CompletableFuture<AggregationResponse> aggregateResponse(final @RequestParam("pricing") List<String> pricing,
                                                                    final @RequestParam("track") List<String> track,
                                                                    final @RequestParam("shipments") List<String> shipments) {
        var request = AggregationRequest.builder()
                .pricing(pricing)
                .track(track)
                .shipments(shipments)
                .build();
        return aggregationService.aggregateResponse(request)
                .orTimeout(10, SECONDS)
                .exceptionally(throwable -> AggregationResponse.builder()
                        .errorResponse(ErrorResponse.builder(throwable, HttpStatusCode.valueOf(500),
                                throwable.getLocalizedMessage()).build()).build());
    }
}
