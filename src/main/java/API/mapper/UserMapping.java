package API.mapper;

import API.entities.User;
import API.entities.request.UserRequest;
import API.entities.response.UserResponse;

public class UserMapping {

	public static User toUser(UserRequest request){

		User user = new User();
		user.setName(request.getName());
		user.setCpf(request.getCpf());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setFunction(request.getFunction());
		user.setTeam(request.getTeam());
		return user;
	}
	public static UserResponse toUserResponse(User user){

		UserResponse response = new UserResponse();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setFunction(user.getFunction());
		response.setTeam(user.getTeam());
		return response;

	}
}
