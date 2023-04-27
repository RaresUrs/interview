package com.tnt.digital.client;

import com.tnt.digital.domain.ShipmentResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ShipmentClient {
    CompletableFuture<ShipmentResponse> getShipments(List<String> orderNumbers);
}
