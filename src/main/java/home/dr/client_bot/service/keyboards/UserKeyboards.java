package home.dr.client_bot.service.keyboards;

import home.dr.client_bot.bot.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class UserKeyboards {

    private final Logger LOG = LoggerFactory.getLogger(UserKeyboards.class);

    public InlineKeyboardMarkup getInlineKeyboard() {

        var userInlineKey1 = InlineKeyboardButton.builder()
                .text("Покажи промо ссылки серверов").callbackData("promo_1")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(userInlineKey1))
                .build();
    }

    public InlineKeyboardMarkup getGreetingKeyboard() {

        var userInlineKey1 = InlineKeyboardButton.builder()
                .text("Покажи промо ссылки серверов").callbackData(Commands.PROMO_LINKS.getName())
                .build();
        var userInlineKey2 = InlineKeyboardButton.builder()
                .text("Управление своей промо ссылкой").callbackData(Commands.MANAGE_SERVERS.getName())
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(userInlineKey1))
                .keyboardRow(List.of(userInlineKey2))
                .build();
    }

}
