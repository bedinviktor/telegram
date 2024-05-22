package com.example.telegrambot.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TelegramCommand {
    START("/start"),
    HELLO("/hello"),
    WORKHOUSE("/workhouse"),
    DEFAULT("");

    private final String command;

    TelegramCommand(String command) {
        this.command = command;
    }

    public static TelegramCommand getCommand(String command) {
        return Arrays.stream(TelegramCommand.values()).filter(x -> x.getCommand().equals(command)).findFirst().orElse(DEFAULT);
    }
}
