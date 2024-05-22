package com.example.telegrambot.DataApiConnector;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Response1 {
    private String error;
    private List<ResponseData> data;
}
