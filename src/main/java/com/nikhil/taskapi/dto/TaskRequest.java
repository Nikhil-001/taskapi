package com.nikhil.taskapi.dto;

import com.nikhil.taskapi.model.Task;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private Task.Status status;
    private LocalDateTime dueDate;
}