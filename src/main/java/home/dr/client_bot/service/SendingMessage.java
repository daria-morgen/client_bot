package home.dr.client_bot.service;

import home.dr.client_bot.domain.User;
import home.dr.client_bot.service.keyboards.AdminKeyboards;
import home.dr.client_bot.service.keyboards.UserKeyboards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.util.Objects;

@Service
public class SendingMessage {

    private final Logger LOG = LoggerFactory.getLogger(SendingMessage.class);

    private final UserKeyboards userKeyboards;

    private final AdminKeyboards adminKeyboards;

    private final DBAccess dbAccess;

    public SendingMessage(UserKeyboards userKeyBoards, AdminKeyboards adminKeyboards, DBAccess dbAccess) {
        this.userKeyboards = userKeyBoards;
        this.adminKeyboards = adminKeyboards;
        this.dbAccess = dbAccess;
    }

    private org.telegram.telegrambots.meta.api.objects.User getUser(Update update) {

        if (!Objects.isNull(update.getMessage())) {
            return update.getMessage().getFrom();
        } else {
            return update.getCallbackQuery().getFrom();
        }
    }

    public SendPhoto getGreetingMessage(Update update) {
        LOG.debug("getGreetingMessage: " + update);

        User user = new User();
        user.setName(update.getMessage().getFrom().getUserName());
        user.setTgId(update.getMessage().getFrom().getId());
        user.setUserDetails(update.getMessage().getFrom().toString());

        dbAccess.createUser(user);
        return SendPhoto.builder()
                .chatId(update.getMessage().getFrom().getId().toString()) //Who are we sending a message to
                .parseMode("HTML").photo(new InputFile(new File("./photos/1.png")))
                .replyMarkup(userKeyboards.getGreetingKeyboard()).build();

    }

    public SendMessage getPromoLinksMessage(Update update) {
        LOG.debug("getPromoLinksMessage: " + update);

        StringBuilder messages = new StringBuilder();
        final var adminServers = dbAccess.getPromoLinks();
        adminServers.forEach(e -> messages.append(e).append("\n===============\n"));

        final var user = getUser(update);
        return SendMessage.builder()
                .chatId(user.getId().toString())
                .disableWebPagePreview(true)
                .text(messages.toString()).build();
    }


    public SendMessage getManageServerMessage(Update update) {
        LOG.debug("getManageServerMessage: " + update);

        final var user = getUser(update);

        final var adminServers = dbAccess.getAdminServers(user.getUserName());

        if (adminServers.isEmpty()) {
            return SendMessage.builder()
                    .chatId(user.getId().toString())
                    .disableWebPagePreview(true)
                    .replyMarkup(adminKeyboards.getCreateServerKeyboard())
                    .text("У тебя нет персональных серверов").build();
        }

        StringBuilder messages = new StringBuilder();
        adminServers.forEach(e -> messages.append(e).append("\n===============\n"));
        return SendMessage.builder()
                .chatId(user.getId().toString())
                .disableWebPagePreview(true)
                .text(messages.toString()).build();
    }

    public SendMessage getAdminMessage(Update update) {
        LOG.debug("getAdminMessage: " + update);

        StringBuilder messages = new StringBuilder();
        final var adminServers = dbAccess.getAdminServers(update.getCallbackQuery().getFrom().getUserName());
        if (adminServers.isEmpty()) {
            return SendMessage.builder()
                    .chatId(update.getCallbackQuery().getFrom().getId().toString())
                    .disableWebPagePreview(true)
                    .replyMarkup(adminKeyboards.getCreateServerKeyboard())
                    .text("У тебя нет персональных серверов").build();
        }
        adminServers.forEach(e -> messages.append(e).append("\n===============\n"));
        return SendMessage.builder()
                .chatId(update.getCallbackQuery().getFrom().getId().toString())
                .disableWebPagePreview(true)
                .text(messages.toString()).build();
    }

    public SendMessage getCreateServerMessage(Update update) {
        LOG.debug("getCreateServerMessage: " + update);

        return SendMessage.builder()
                .chatId(update.getCallbackQuery().getFrom().getId().toString())
                .disableWebPagePreview(true)
                .text("Создание персональных серверов находится в разработке").build();
    }

    public SendMessage getServersMessage(Update update) {
        LOG.debug("getServersMessage: " + update);

        StringBuilder messages = new StringBuilder();
        dbAccess.getServers().forEach(e -> messages.append(e).append("\n===============\n"));

        return SendMessage.builder()
                .chatId(update.getMessage().getFrom().getId().toString()) //Who are we sending a message to
                .text(messages.toString())
                .disableWebPagePreview(true)
                .build();
    }
}
