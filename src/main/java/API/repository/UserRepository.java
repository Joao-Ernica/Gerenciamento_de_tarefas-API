package API.repository;

import API.entities.User;
import API.entities.enums.UserFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByFunction(UserFunction function);
}
