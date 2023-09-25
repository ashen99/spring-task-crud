package com.ashen.springwithmongo.service;

import com.ashen.springwithmongo.model.Task;
import com.ashen.springwithmongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    //CRUD CREATE, READ, UPDATE, DELETE

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return taskRepository.getTaskByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest){
        //get the existing document from DB
        //populate new value from request to existing object/entity/document
        Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId+" task deleted";
    }

}
