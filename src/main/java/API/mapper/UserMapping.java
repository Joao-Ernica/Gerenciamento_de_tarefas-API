package API.mapper;

import API.entities.User;
import API.entities.request.UserRequest;
import API.entities.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserMapping {

	private final ModelMapper mapper;

	public User toUser(UserRequest request) {
		return mapper.map(request, User.class);
	}

	public UserResponse toUserResponse(User user) {
		return mapper.map(user, UserResponse.class);
	}


	public List<UserResponse> toUserResponseList(List<User> user) {
		return user.
				stream()
				.map(this::toUserResponse)
				.collect(Collectors.toList());
	}

//	public static User toUser(UserRequest request){
//
//		User user = new User();
//		user.setName(request.getName());
//		user.setCpf(request.getCpf());
//		user.setEmail(request.getEmail());
//		user.setPassword(request.getPassword());
//		user.setFunction(request.getFunction());
//		user.setTeam(request.getTeam());
//		return user;
//	}
//	public static UserResponse toUserResponse(User user){
//
//		UserResponse response = new UserResponse();
//		response.setName(user.getName());
//		response.setEmail(user.getEmail());
//		response.setFunction(user.getFunction());
//		response.setTeam(user.getTeam());
//		return response;
//  }
}

