package ru.alexmenov.taskmanagementsystem.repository.task;

import org.springframework.data.repository.Repository;
import ru.alexmenov.taskmanagementsystem.model.task.Task;

import java.util.Optional;

public interface TaskRepository extends Repository<Task, Long> {

    Optional<Task> findTaskByTaskId(long taskId);

    Task save(Task task);

    void deleteTaskByTaskId(long taskId);

}
