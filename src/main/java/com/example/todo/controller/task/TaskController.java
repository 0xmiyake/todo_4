package com.example.todo.controller.task;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
	
	@GetMapping("/tasks")
	public String list(Model model) {
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
		var taskList = List.of(task1, task2);
		// "taskList"の値がth:each="task: ${taskList}"に入る
		model.addAttribute("taskList", taskList); 
		return "tasks/list";
	}
}
