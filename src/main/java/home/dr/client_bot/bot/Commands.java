package home.dr.client_bot.bot;

import java.util.Arrays;

public enum Commands {

    GREETING("/greeting"),

    SERVERS("/servers"),

    PROMO_LINKS("/promo_servers"),

    MANAGE_SERVERS("/manage_servers"),

    SHOW_MY_SERVERS("/show_my_servers"),

    CREATE_SERVER("/create_server");

    private final String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Commands getCommand(String command) {
        final var first = Arrays.stream(values())
                .filter(e -> e.name.equals(command))
                .findFirst();
        System.out.println(first);
        return first.orElse(GREETING);

    }
}
