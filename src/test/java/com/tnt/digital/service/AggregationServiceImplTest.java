package com.tnt.digital.service;

import com.tnt.digital.client.PriceClient;
import com.tnt.digital.client.ShipmentClient;
import com.tnt.digital.client.TrackClient;
import com.tnt.digital.domain.AggregationRequest;
import com.tnt.digital.domain.PricingResponse;
import com.tnt.digital.domain.ShipmentResponse;
import com.tnt.digital.domain.TrackResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AggregationServiceImplTest {

    @Mock
    private ShipmentClient shipmentClient;

    @Mock
    private PriceClient priceClient;

    @Mock
    private TrackClient trackClient;

    @InjectMocks
    private AggregationServiceImpl aggregationService;

    @Test
    void aggregateResponse() {

        var request = AggregationRequest.builder()
                .shipments(List.of("123456789"))
                .pricing(List.of("FR"))
                .track(List.of("123456789"))
                .build();
        var track = CompletableFuture.completedFuture(TrackResponse.builder()
                .track(Map.of("A", "A"))
                .build());
        var shipment = CompletableFuture.completedFuture(ShipmentResponse.builder()
                .shipments(Map.of("A", List.of("A")))
                .build());
        var price = CompletableFuture.completedFuture(PricingResponse.builder()
                .price(Map.of("A", 1.0))
                .build());
        when(trackClient.getTrack(request.getTrack())).thenReturn(track);
        when(shipmentClient.getShipments(request.getShipments())).thenReturn(shipment);
        when(priceClient.getPrice(request.getPricing())).thenReturn(price);

        var aggregationResponseCompletableFuture = aggregationService.aggregateResponse(request).join();

        assertThat(aggregationResponseCompletableFuture.getTrack()).isEqualTo(track.join().getTrack());
        assertThat(aggregationResponseCompletableFuture.getPricing()).isEqualTo(price.join().getPrice());
        assertThat(aggregationResponseCompletableFuture.getShipments()).isEqualTo(shipment.join().getShipments());
    }
}