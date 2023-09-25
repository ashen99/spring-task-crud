package com.ashen.springwithmongo.controller;

import com.ashen.springwithmongo.model.Task;
import com.ashen.springwithmongo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return service.addTask(task);
    }

    @GetMapping
    public List<Task> getTasks(){
        return service.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId){
        return service.getTaskByTaskId(taskId);
    }

    public List<Task> getTaskBySeverity(int severity){
        return taskRepository.findBySeverity(severity);
    }
}
