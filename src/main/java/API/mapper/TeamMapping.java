package API.mapper;

import API.entities.Team;
import API.entities.request.TeamRequest;
import API.entities.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamMapping {

	private final ModelMapper mapper;

	public Team toTeam(TeamRequest request) {
		return mapper.map(request, Team.class);
	}

	public TeamResponse toTeamResponse(Team team) {
		return mapper.map(team, TeamResponse.class);
	}

	public List<TeamResponse> toTeamResponseList(List<Team> team) {
		return team.
				stream()
				.map(this::toTeamResponse)
				.collect(Collectors.toList());
	}
}