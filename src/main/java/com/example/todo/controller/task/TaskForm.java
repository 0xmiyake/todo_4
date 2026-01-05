package com.example.todo.controller.task;

import com.example.todo.service.task.TaskEntity;
import com.example.todo.service.task.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record TaskForm(
		@NotBlank //79: バリデーション 必須入力
		@Size(max = 256, message = "256文字以内で入力してください。")
		String summary,
		String description,
		@NotBlank
		String status
		) {

	public TaskEntity toEntity() {
		// TODO 自動生成されたメソッド・スタブ
		return new TaskEntity(null, summary(), description(), TaskStatus.valueOf(status()));
	}

}
