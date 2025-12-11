package com.example.todo.service.task;

import java.util.List;

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
}
