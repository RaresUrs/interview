package com.tnt.digital.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnt.digital.config.properties.WebClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final WebClientProperties webClientProperties;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(webClientProperties.getBaseUrl())
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
    }
}
