package com.app.todolist.controller;

import com.app.todolist.model.Task;
import com.app.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public  String getTasks(Model model){
        List<Task> taskList = taskService.getTaskList();
        model.addAttribute("tasks",taskList);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("{id}/complete")
    public String markComplete(@PathVariable Long id){
        taskService.markComplete(id);
        return "redirect:/";
    }
}
