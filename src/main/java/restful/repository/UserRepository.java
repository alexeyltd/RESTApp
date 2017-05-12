package restful.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restful.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "select u from User u where login =:username")
    User findByUserName(@Param("username") String username);

}
