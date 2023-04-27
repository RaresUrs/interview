package com.tnt.digital.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.ErrorResponse;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class AggregationResponse {

    private Map<String, Double> pricing;
    private Map<String, String> track;
    private Map<String, List<String>> shipments;
    private ErrorResponse errorResponse;
}
