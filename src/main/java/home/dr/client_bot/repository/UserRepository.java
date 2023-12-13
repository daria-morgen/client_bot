package home.dr.client_bot.repository;

import home.dr.client_bot.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);
//    @Modifying
//    @Query("UPDATE person SET first_name = :name WHERE id = :id")
//    boolean updateByFirstName(@Param("id") Long id, @Param("name") String name);

}
