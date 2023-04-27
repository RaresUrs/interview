package com.tnt.digital.controller;

import com.tnt.digital.domain.AggregationRequest;
import com.tnt.digital.domain.AggregationResponse;
import com.tnt.digital.service.AggregationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AggregationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AggregationService aggregationService;

    @Test
    void aggregateResponse() throws Exception {
        var pricing = List.of("NL", "CN");
        var track = List.of("109347263", "123456891");
        var shipments = List.of("109347263", "123456891");
        var request = AggregationRequest.builder()
                .pricing(pricing)
                .track(track)
                .shipments(shipments)
                .build();
        var expectedResponse = AggregationResponse.builder()
                .pricing(Map.of("NL", 10.0, "CN", 20.0))
                .track(Map.of("109347263", "delivered", "123456891", "in_transit"))
                .shipments(Map.of("109347263", List.of("dispatched"), "123456891", List.of("pending")))
                .build();

        // Define mock behavior for the service
        when(aggregationService.aggregateResponse(request)).thenReturn(
                CompletableFuture.completedFuture(expectedResponse));

        // Perform the request and check the response
        mockMvc.perform(get("/aggregation")
                        .param("pricing", "NL,CN")
                        .param("track", "109347263,123456891")
                        .param("shipments", "109347263,123456891"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        // Verify that the service was called with the correct request
        verify(aggregationService).aggregateResponse(request);
    }
}