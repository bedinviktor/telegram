package com.example.telegrambot.service;

import com.example.telegrambot.DataApiConnector.ApiController;
import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.enums.TelegramCommand;
import com.example.telegrambot.message.ConstantsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final ConstantsMessage message = new ConstantsMessage();
    private final ApiController apiController;


    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            TelegramCommand command = TelegramCommand.getCommand(messageText);
            switch (command) {
                case START:
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case HELLO:
                    inlineButton(chatId, ConstantsMessage.WORK_HOUSE);
                    apiController.CommandHost("getUsers");
                    break;
                case WORKHOUSE:
                    sendPhoto(chatId);
                default:
                    sendMassage(chatId, ConstantsMessage.ERROR_MESSAGE);
            }
        }
    }

    public void sendPhoto(long chatId) {
        try {
            File image = ResourceUtils.getFile(config.getWork_schedule());
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile(image));
            sendPhoto.setChatId(chatId);
            execute(sendPhoto);
        } catch (FileNotFoundException | TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void inlineButton(long chatId, String textToSend) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton urlButton = new InlineKeyboardButton();

        urlButton.setText(ConstantsMessage.URL_MESSAGE);
        urlButton.setUrl(config.getUrl_cmd());
        rowInline.add(urlButton);

        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    private void startCommandReceived(long chatId, String name) {
        sendMassage(chatId, message.helloMessage(name));
    }

    public void sendMassage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
