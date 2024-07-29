package API.service;

import API.entities.Team;
import API.entities.enums.TeamFunction;
import API.repository.TeamRepository;
import API.service.exception.IllegalArgumentException;
import API.service.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

	@Autowired
	private TeamRepository repository;

	public List<Team> findAll() {
		return repository.findAll();
	}

	public Team findById(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException(id));  // se não puder lançar o get ira lançar uma excepetions
	}

	public List<Team> findByFunction(TeamFunction function) {
		return repository.findByFunction(function);

	}

	public Team insert(Team obj) {
		List<Team> all = repository.findAll();
		for (Team team : all) {
			if(team.getName().equalsIgnoreCase(obj.getName())) {
				throw new IllegalArgumentException("Esse nome já existe");
			}
		}
		return repository.save(obj);
	}

	public Team update(Long id, Team obj) {
		Team byId = repository.findById(id).orElseThrow(()
				-> new IllegalArgumentException("Id não encontrado"));

		if(repository.existsByNameAndIdNot(obj.getName(), id)) {
			throw new java.lang.IllegalArgumentException("Nome já existe");
		}

		BeanUtils.copyProperties(obj, byId, "id");
		return repository.save(byId);

	}

}
