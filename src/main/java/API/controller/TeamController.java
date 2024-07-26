package API.controller;

import API.entities.Team;
import API.entities.enums.TeamFunction;
import API.entities.request.TeamRequest;
import API.entities.response.TeamResponse;
import API.mapper.TeamMapping;
import API.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {

	@Autowired
	private TeamService service;

	@Autowired
	private final TeamMapping mapping;

	@GetMapping
	public List<TeamResponse> findAll(){
		List<Team> team = service.findAll();
		return mapping.toTeamResponseList(team);
	}

	@GetMapping("{id}")
	public TeamResponse findById(@PathVariable Long id){
		Team byId = service.findById(id);
		return mapping.toTeamResponse(byId);
	}

	@GetMapping("function/{function}")
	public List<TeamResponse> findByFunction(@PathVariable TeamFunction function){
		List<Team> team = service.findByFunction(function);
		return mapping.toTeamResponseList(team);

	}


	@PostMapping
	public TeamResponse insert(@RequestBody TeamRequest obj){
		Team team = mapping.toTeam(obj);
		Team insert = service.insert(team);
		return mapping.toTeamResponse(insert);

	}

	@PutMapping("{id}")
	public TeamResponse update(@PathVariable Long id, @RequestBody TeamRequest obj){
		Team team = mapping.toTeam(obj);
		Team update = service.update(id, team);
		return mapping.toTeamResponse(update);
	}

	//update

}
