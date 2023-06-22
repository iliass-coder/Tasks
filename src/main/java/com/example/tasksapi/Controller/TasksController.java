package com.example.tasksapi.Controller;

import com.example.tasksapi.Module.Task;
import com.example.tasksapi.Repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;


    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){

        List<Task> tasks = tasksRepository.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getaTask(@PathVariable(value = "id") Long idTask){

        Optional<Task> task = tasksRepository.findById(idTask);

        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody Task task){

        tasksRepository.save(task);

        return new ResponseEntity<>(task.getId(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> updateTask(@PathVariable(value = "id") Long taskId, @RequestBody Task task){
        Optional<Task> optionalTask = tasksRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task taskUpdte = optionalTask.get();
            taskUpdte.setTitle(task.getTitle());
            taskUpdte.setDescription(task.getDescription());

            tasksRepository.save(taskUpdte);

            return new ResponseEntity<>(task.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable(value = "id") Long idTask){

        tasksRepository.deleteById(idTask);

        return new ResponseEntity<>(idTask,HttpStatus.OK);
    }

}
