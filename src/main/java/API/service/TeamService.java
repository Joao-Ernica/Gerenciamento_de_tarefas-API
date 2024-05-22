package API.service;

import API.entities.Team;
import API.repository.TeamRepository;
import API.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

	@Autowired
	private TeamRepository repository;

	public List<Team> findAll() {
		return repository.findAll();
	}

	public Team findById(Long id) {
		Optional<Team> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // se não puder lançar o get ira lançar uma excepetions
	}
}
