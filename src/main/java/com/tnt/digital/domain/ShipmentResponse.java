package com.tnt.digital.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ShipmentResponse {
    private Map<String, List<String>> shipments;
}
