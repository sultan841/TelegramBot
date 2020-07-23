import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    private static final String TOKEN = "1323850397:AAFP-KgL6Z2ovPN9Ftxe_b_n6KMdNEYSMfk";

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
           if (message.getText().equals("/start")) {
               sendMsg(message, "Ваше ФИО в формате 'Арманов Арман Арманович'", false);
           }

           else if (   message.getText().split(" ").length == 3) {
                sendMsg(message, "Ваш год рождения в формате 'YYYY-MM-DD'", false);
            }
            else if (message.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                sendMsg(message, "Ваш номер телефона", false);
            }
            else if (message.getText().equals("Da")) {
                sendMsg(message,  "https://www.google.com/", false);
            }
            else if (message.getText().equals("No")) {
                sendMsg(message, "к сожалению мы не можем... ", false);
           }
            if (message.getText().length() == 11 && message.getText().startsWith("87") || message.getText().startsWith("+7")) {
                sendMsg(message, "Подтверждaете??", true);
            }
        }
    }



    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Da"));
        keyboardFirstRow.add(new KeyboardButton("Net"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    private void sendMsg(Message message, String text, boolean flags) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableMarkdown(true);
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            if (flags) {
                setButtons(sendMessage);
            }
            else {
                sendMessage.setReplyMarkup(null);
            }
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}


