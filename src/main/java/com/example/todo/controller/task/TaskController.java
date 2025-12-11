package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.service.task.TaskService;

@Controller
public class TaskController {
	private final TaskService taskService;
	//private final TaskService taskService = new TaskService();
	
	// 依存性注入（DI: Dependency Injection）とは、
	//“必要なオブジェクトは 自分で直接 new せず、外部から与えてもらう” という仕組み・考え方
	
	// ここでSpring が取り出して注入してくれる（＝Bean取得）
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("/tasks")
	public String list(Model model) {
		var taskList = taskService.find() // List<TaskEntity>が入っているこれをList<TaskDTO>
			.stream()
			.map(TaskDTO:: toDTO)
			.toList();
		model.addAttribute("taskList", taskList);
		
		return "tasks/list";
	}
}
