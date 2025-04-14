package com.app.todolist.service;

import com.app.todolist.model.Task;
import com.app.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTaskList(){
        return taskRepository.findAll();
    }


    public void createTask(String taskTitle) {
        Task newTask = new Task();
        newTask.setTaskTitle(taskTitle);
        newTask.setTaskComplete(false);
        taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void markComplete(Long id) {

            Task existingTask = taskRepository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid task id"));
            existingTask.setTaskComplete(!existingTask.isTaskComplete());
            taskRepository.save(existingTask);
    }
}
