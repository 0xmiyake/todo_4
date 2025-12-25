package com.example.todo.controller.task;

import com.example.todo.service.task.TaskEntity;
import com.example.todo.service.task.TaskStatus;

public record TaskFrom(
		String summary,
		String description,
		String status
		) {

	public TaskEntity toEntity() {
		// TODO 自動生成されたメソッド・スタブ
		return new TaskEntity(null, summary(), description(), TaskStatus.valueOf(status()));
	}

}
