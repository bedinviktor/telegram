package com.example.telegrambot.DataApiConnector;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseDto {
    private String error;
    private List<ResponseName> data;
}
