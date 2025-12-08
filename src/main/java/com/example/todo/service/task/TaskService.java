package com.example.todo.service.task;

import java.util.List;

import com.example.todo.controller.task.TaskDTO;

public class TaskService {
	
	public List<TaskDTO> find(){
		var task1 = new TaskDTO(
				1L, 
				"Springを学ぶ", 
				"TODOアプリを作ってみる", 
				"TODO"
		);
		var task2 = new TaskDTO(
				2L, 
				"Springのセキュリティを学ぶ", 
				"ログイン機能の作成", 
				"TODO"
		);
		return List.of(task1, task2);
	}
}
