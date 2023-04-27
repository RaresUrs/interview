package com.tnt.digital.client;

import com.tnt.digital.domain.TrackResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TrackClient {
    CompletableFuture<TrackResponse> getTrack(List<String> orderNumbers);
}
