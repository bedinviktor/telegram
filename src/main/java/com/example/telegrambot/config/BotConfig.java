package com.example.telegrambot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.yaml")
public class BotConfig  {


    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

    @Value("${bot.owner}")
    String owner;

    @Value("${url_cmd}")
    String url_cmd;

    @Value("${work_schedule}")
    String work_schedule;
}
