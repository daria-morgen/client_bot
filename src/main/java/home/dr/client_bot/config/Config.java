package home.dr.client_bot.config;

import home.dr.client_bot.bot.Bot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class Config {

    private final Bot bot;

    public Config(Bot bot) {
        this.bot = bot;
    }

    @Bean
    public Bot newBot() throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);

        return bot;
    }
}
