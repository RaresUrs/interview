package com.tnt.digital.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web-client")
@Data
public class WebClientProperties {
    private String baseUrl;
}
