package home.dr.client_bot.repository;

import home.dr.client_bot.domain.Favorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Long> {
}
