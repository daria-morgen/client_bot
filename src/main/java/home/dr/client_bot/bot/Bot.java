package home.dr.client_bot.bot;

import home.dr.client_bot.service.SendingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${botName}")
    private String botName;

    @Value("${botToken}")
    private String botToken;

    private final SendingMessage sendingMessage;

    private final Logger LOG = LoggerFactory.getLogger(Bot.class);


    public Bot(SendingMessage sendingMessage) {
        this.sendingMessage = sendingMessage;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        LOG.info("Update: " + update.toString());

        Commands currentCommand = Commands.GREETING;

        if (!Objects.isNull(update.getMessage())) {
            if (update.getMessage().isCommand()) {
                currentCommand = Commands.getCommand(update.getMessage().getText());
            }
        }  //Call a custom method
        else {
            currentCommand = Commands.getCommand(update.getCallbackQuery().getData());
        }


        switch (currentCommand) {
            case PROMO_LINKS -> sendMessage(sendingMessage.getPromoLinksMessage(update));
            case MANAGE_SERVERS -> sendMessage(sendingMessage.getManageServerMessage(update));
            case SERVERS -> sendMessage(sendingMessage.getServersMessage(update));
            case SHOW_MY_SERVERS -> sendMessage(sendingMessage.getAdminMessage(update));
            case CREATE_SERVER -> sendMessage(sendingMessage.getCreateServerMessage(update));
            default -> sendMessage(sendingMessage.getGreetingMessage(update));
        }
    }

    public void sendMessage(SendPhoto sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendMessage sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}