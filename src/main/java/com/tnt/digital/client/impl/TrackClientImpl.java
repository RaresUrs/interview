package com.tnt.digital.client.impl;

import com.tnt.digital.client.TrackClient;
import com.tnt.digital.domain.TrackResponse;
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
public class TrackClientImpl implements TrackClient {
    private static final String TRACK_ENDPOINT = "/track";
    private static final String TRACK_SERVICE_ERROR_MESSAGE = "Track Service Unavailable";
    private final WebClient webClient;
    private final OrderNumberValidator orderNumberValidator;

    @Override
    public CompletableFuture<TrackResponse> getTrack(List<String> orderNumbers) {
        orderNumberValidator.validateOrderNumbers(orderNumbers);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(TRACK_ENDPOINT)
                        .query(getQueryParams(orderNumbers))
                        .build())
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.SERVICE_UNAVAILABLE),
                        response -> Mono.error(new ServiceUnavailableException(TRACK_SERVICE_ERROR_MESSAGE)))
                .bodyToMono(Map.class)
                .map(this::buildResponse)
                .toFuture();
    }

    private TrackResponse buildResponse(Map<String, String> map) {
        return TrackResponse.builder()
                .track(map)
                .build();
    }
}
