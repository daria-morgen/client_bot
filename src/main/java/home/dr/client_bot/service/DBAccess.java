package home.dr.client_bot.service;

import home.dr.client_bot.domain.User;
import home.dr.client_bot.repository.FavoritesRepository;
import home.dr.client_bot.repository.ServerRepository;
import home.dr.client_bot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBAccess {

    private final Logger LOG = LoggerFactory.getLogger(DBAccess.class);


    private final UserRepository userRepository;
    private final ServerRepository serverRepository;
    private final FavoritesRepository favoritesRepository;

    public DBAccess(UserRepository userRepository, ServerRepository serverRepository, FavoritesRepository favoritesRepository) {
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
        this.favoritesRepository = favoritesRepository;
    }

    public String createUser(User user) {
        LOG.debug("createUser: " + user);

        if (userRepository.findByName(user.getName()).isEmpty())
            userRepository.save(user);

        return null;
    }

    public List<String> getPromoLinks() {
        LOG.debug("getPromoLinks");

        final var all = serverRepository.findAll();
        LOG.debug("serverRepository.findAll: all.iterator().hasNext(): " + all.iterator().hasNext());

        List<String> servers = new ArrayList<>();
        if (all.iterator().hasNext()) {
            all.forEach(e -> {
                servers.add(e.getLink() + "\n");
            });
        } else {
            servers.add(
                    "https://1.mts.ru/promo/ar_fog_game/\n");

            servers.add(
                    "https://2.mts.ru/promo/poshlinaher_so_skidkami/\n");
        }
        return servers;
    }

    public List<String> getServers() {
        LOG.debug("getServers");

        final var all = serverRepository.findAll();
        List<String> servers = new ArrayList<>();

        if (all.iterator().hasNext()) {
            all.forEach(e -> {
                servers.add("Сервера" + e.getName() + ": \n" +
                        e.getLink() + "\n" +
                        "Локация серверов: " + e.getLocation() + "\n" +
                        "Контакт: " + e.getContact());
            });

        } else {
            servers.add("Сервера 1: \n" +
                    "https://fogplay.mts.ru/promo/ar_fog_game/\n" +
                    "Локация серверов: Москва\n" +
                    "Контакт @tglink");

            servers.add("Сервера 2: \n" +
                    "https://fogplay.mts.ru/promo/poshlinaher_so_skidkami/\n" +
                    "Локация серверов: Москва\n" +
                    "Контакт @tglink");
        }
        return servers;
    }


    //todo
    public List<String> getAdminServers(String userName) {
        LOG.debug("getAdminServers: " + userName);
        List<String> servers = new ArrayList();


        return servers;
    }
}
