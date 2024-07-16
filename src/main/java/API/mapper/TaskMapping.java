package API.mapper;

import API.entities.Task;
import API.entities.request.TaskRequest;
import API.entities.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapping {

	private final ModelMapper mapper;

	public Task toTask(TaskRequest request) {
		return mapper.map(request, Task.class);
	}

	public TaskResponse toTaskResponse(Task task) {
		return mapper.map(task, TaskResponse.class);
	}

	public List<TaskResponse> toTaskResponseList(List<Task> task) {
		return task.
				stream()
				.map(this::toTaskResponse)
				.collect(Collectors.toList());
	}
}