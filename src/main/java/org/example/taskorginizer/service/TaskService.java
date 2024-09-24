package org.example.taskorginizer.service;

import org.example.taskorginizer.entity.Task;
import org.example.taskorginizer.exception.TaskIdNotFoundException;
import org.example.taskorginizer.exception.TaskNotFoundException;
import org.example.taskorginizer.repo.TaskRepo;
import org.example.taskorginizer.service.impl.TaskImpel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//service layer for adding,updating,deleting,retrieving of the tasks
@Service
public class TaskService implements TaskImpel {

    @Autowired
    TaskRepo taskRepo;
    //save task
    @Override
    public Task saveTask(Task task) {

        if(taskRepo.existsById(task.getTaskId())){
            throw new TaskIdNotFoundException("Task Id already exists");
        }
        return taskRepo.save(task);
    }

    //get all tasks
    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    //get task according to id  if not found throw TaskIdNotFoundException
    @Override
    public Task getTaskById(Long id) {
        if(id == null) {
            throw new TaskIdNotFoundException("Id is Null");
        }
        return taskRepo.findById(id).orElse(null);
    }

    //update task according to id if not found throw TaskNotFoundException
    @Override
    public Task updateTask(Long id,Task task) {
        Task existingTask = taskRepo.findById(id).orElse(null);
        if(existingTask == null) {
            throw new TaskNotFoundException("Task is Null");
        }

        return taskRepo.save(existingTask);
    }

    //delete task according to id
    @Override
    public void deleteTask(Long id) {
        if(!taskRepo.existsById(id)){
            throw new TaskIdNotFoundException("Task Id Not Found");
        }
        taskRepo.deleteById(id);
    }
}
