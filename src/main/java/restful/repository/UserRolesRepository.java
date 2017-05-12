package restful.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restful.model.UserRole;

import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

    @Query(value = "select r FROM UserRole r WHERE r.name = :name")
     List<String> findRoleByUserName(@Param("name") String username);


}
