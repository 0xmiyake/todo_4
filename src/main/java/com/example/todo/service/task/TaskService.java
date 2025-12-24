package com.example.todo.service.task;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todo.repository.task.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service // ここでBean登録をする
@RequiredArgsConstructor
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public List<TaskEntity> find(){
		return taskRepository.select();
	}

	public Optional<TaskEntity> findById(long taskId) {
		return taskRepository.selectById(taskId);
		
	}

	public void create(TaskEntity newEntity) {
		taskRepository.insert(newEntity);
	}
}
