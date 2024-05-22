package com.example.telegrambot.massege;

public class ConstantsMessage {

    public static final String WORK_HOUSE = "Для записи к врачу пройдите по ссылке";

    public static final String ERROR_MESSAGE = "Ошибка, не знаю что ответить";

    public static final String URL_MESSAGE = "Перейти на сайт";

    public String helloMessage(String name){
        return String.format("Привет %s, Бот работает!", name);
    }
}
