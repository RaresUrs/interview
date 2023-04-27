package com.tnt.digital.service;

import com.tnt.digital.client.PriceClient;
import com.tnt.digital.client.ShipmentClient;
import com.tnt.digital.client.TrackClient;
import com.tnt.digital.domain.AggregationRequest;
import com.tnt.digital.domain.AggregationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.allOf;

@Service
@RequiredArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private final ShipmentClient shipmentClient;
    private final PriceClient priceClient;
    private final TrackClient trackClient;

    @Override
    public CompletableFuture<AggregationResponse> aggregateResponse(AggregationRequest request) {
        var shipmentResponseFuture = shipmentClient.getShipments(request.getShipments());
        var pricingResponseFuture = priceClient.getPrice(request.getPricing());
        var trackingResponseFuture = trackClient.getTrack(request.getTrack());

        return allOf(shipmentResponseFuture, pricingResponseFuture, trackingResponseFuture)
                .thenApply((Void) -> {
                    var shipmentResponses = shipmentResponseFuture.join();
                    var pricingResponse = pricingResponseFuture.join();
                    var trackingResponse = trackingResponseFuture.join();
                    return AggregationResponse.builder()
                            .pricing(pricingResponse.getPrice())
                            .shipments(shipmentResponses.getShipments())
                            .track(trackingResponse.getTrack())
                            .build();
                })
                .exceptionally(ex -> {
                    throw new RuntimeException("Error in calling downstream APIs: ", ex);
                });
    }
}
