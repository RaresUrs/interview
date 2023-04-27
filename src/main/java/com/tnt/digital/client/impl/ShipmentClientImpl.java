package com.tnt.digital.client.impl;

import com.tnt.digital.client.ShipmentClient;
import com.tnt.digital.domain.ShipmentResponse;
import com.tnt.digital.validator.OrderNumberValidator;
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
public class ShipmentClientImpl implements ShipmentClient {
    private static final String SHIPMENTS_ENDPOINT = "/shipments";
    private static final String SHIPMENT_SERVICE_ERROR_MESSAGE = "Shipment Service Unavailable";
    private final WebClient webClient;
    private final OrderNumberValidator orderNumberValidator;

    @Override
    public CompletableFuture<ShipmentResponse> getShipments(List<String> orderNumbers) {
        orderNumberValidator.validateOrderNumbers(orderNumbers);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(SHIPMENTS_ENDPOINT)
                        .query(getQueryParams(orderNumbers))
                        .build())
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.SERVICE_UNAVAILABLE),
                        response -> Mono.error(new ServiceUnavailableException(SHIPMENT_SERVICE_ERROR_MESSAGE)))
                .bodyToMono(Map.class)
                .map(this::buildResponse)
                .toFuture();
    }

    private ShipmentResponse buildResponse(Map<String, List<String>> map) {
        return ShipmentResponse.builder()
                .shipments(map)
                .build();
    }
}
