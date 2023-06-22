package com.example.tasksapi.Repository;

import com.example.tasksapi.Module.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task,Long>{
}
