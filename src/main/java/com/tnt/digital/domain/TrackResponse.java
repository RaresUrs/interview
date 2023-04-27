package com.tnt.digital.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class TrackResponse {
    private Map<String, String> track;
}
