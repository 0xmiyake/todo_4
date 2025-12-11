package com.example.todo.service.task;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	public List<TaskEntity> find(){
		var task1 = new TaskEntity(
				1L, 
				"Springを学ぶ", 
				"TODOアプリを作ってみる", 
				TaskStatus.TODO
		);
		var task2 = new TaskEntity(
				2L, 
				"Springのセキュリティを学ぶ", 
				"ログイン機能の作成", 
				TaskStatus.DOING
		);
		return List.of(task1, task2);
	}
}
