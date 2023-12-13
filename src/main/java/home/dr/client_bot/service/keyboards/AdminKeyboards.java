package home.dr.client_bot.service.keyboards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class AdminKeyboards {
    private final Logger LOG = LoggerFactory.getLogger(AdminKeyboards.class);


    public InlineKeyboardMarkup getInlineKeyboard() {

        var adminInlineKeyboard2 = InlineKeyboardButton.builder()
                .text("Управление серверами").callbackData("/show_my_servers")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(adminInlineKeyboard2))
                .build();

    }

    public ReplyKeyboard getCreateServerKeyboard() {
        var adminInlineKeyboard2 = InlineKeyboardButton.builder()
                .text("Создать сервер").callbackData("/create_server")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(adminInlineKeyboard2))
                .build();
    }
}
