package com.tnt.digital.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AggregationRequest {
    private List<String> pricing;
    private List<String> track;
    private List<String> shipments;
}
