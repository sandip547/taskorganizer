package org.example.taskorginizer.service.impl;

import org.example.taskorginizer.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskImpel {

    public Task saveTask(Task task);
    public List<Task> getAllTasks();
    public Task getTaskById(Long id);
    public Task updateTask(Long id,Task task);
    public void deleteTask(Long id);
}
