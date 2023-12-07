package ru.alexmenov.taskmanagementsystem.dto.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.alexmenov.taskmanagementsystem.model.task.BaseTask;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@FieldDefaults(level = PRIVATE)
public class TaskPutUpdateDTO extends BaseTask {

    Long taskId;

}
