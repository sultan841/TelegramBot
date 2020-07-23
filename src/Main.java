import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] arg) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TelegramBot telegramBot = new TelegramBot();

        try {
            telegramBotsApi.registerBot(telegramBot);
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
