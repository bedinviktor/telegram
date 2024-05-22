package com.example.telegrambot.DataApiConnector;

import com.example.telegrambot.config.ApiRnova;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ApiController {
    private final ApiRnova apiRnova;

    public void CommandHost(String endpoint) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiRnova.hostEndpoints(endpoint))
                .build();

        try (
                Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto response1 = objectMapper.readValue(responseBody, ResponseDto.class);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
