package com.example.telegrambot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(value = "api")
public class ApiRnova {

    private String host;
    private String api_key;
    public String hostEndpoints(String endpoint) {
        ObjectMapper objectMapper = new ObjectMapper();

        return String.format("%s%s?api_key=%s", host, endpoint, api_key);
    }
}
