package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
	
	@GetMapping("/tasks")
	public String list(Model model) {
		model.addAttribute("task", "Springを学ぶ");
		return "tasks/list";
	}
}
