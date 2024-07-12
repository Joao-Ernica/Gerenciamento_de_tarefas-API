package API.repository;

import API.entities.Task;
import API.entities.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByTaskStatus(TaskStatus status);
	List<Task> findAllByOrderByFinalizationDateDesc();
	List<Task> findAllByOrderByFinalizationDateAsc();
	List<Task> findByFinalizationDateBetween(LocalDate dataInicial, LocalDate dataFinal);

}
