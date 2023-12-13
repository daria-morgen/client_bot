package home.dr.client_bot.repository;

import home.dr.client_bot.domain.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends CrudRepository<Server, Long> {

}
