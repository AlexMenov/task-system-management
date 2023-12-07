package ru.alexmenov.taskmanagementsystem.service.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexmenov.taskmanagementsystem.dto.task.TaskPostCreateDTO;
import ru.alexmenov.taskmanagementsystem.dto.task.TaskPutUpdateDTO;
import ru.alexmenov.taskmanagementsystem.dto.task.TaskResponseDTO;
import ru.alexmenov.taskmanagementsystem.mapper.TaskMapper;
import ru.alexmenov.taskmanagementsystem.model.task.Task;
import ru.alexmenov.taskmanagementsystem.repository.task.TaskRepository;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;
    EntityManager entityManager;

    public TaskResponseDTO findTaskById(long taskId) {
        return taskRepository
                .findTaskByTaskId(taskId)
                .map(taskMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public TaskResponseDTO createTask(TaskPostCreateDTO createDTO) {
        Task task = taskMapper.toEntity(createDTO);
        entityManager.persist(task);
        return taskMapper.toResponse(task);
    }

    public TaskResponseDTO updateTask(long taskId,
                                      TaskPutUpdateDTO updateDTO) {
        Task task = entityManager.find(Task.class, taskId);
        if (task == null) {
            throw new EntityNotFoundException("Task not found");
        }
        taskMapper.toEntity(updateDTO, task);
        return taskMapper.toResponse(task);
    }

    public void deleteTaskById(long taskId) {
        taskRepository.deleteTaskByTaskId(taskId);
    }

}
