package com.tnt.digital.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "track")
@Data
public class TrackConfigurationProperties {
    private String baseUrl;
    private int port;
}
