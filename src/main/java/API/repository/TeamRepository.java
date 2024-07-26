package API.repository;

import API.entities.Team;
import API.entities.enums.TeamFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findByFunction(TeamFunction function);
	boolean existsByNameAndIdNot(String name, Long id);

}
