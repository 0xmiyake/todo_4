package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.service.task.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // 39: Lombockを使ってボイラープレートコードの削減
public class TaskController {
	private final TaskService taskService;

	
	@GetMapping("/tasks")
	public String list(Model model) {
		var taskList = taskService.find() // List<TaskEntity>が入っているこれをList<TaskDTO>
			.stream()
			.map(TaskDTO:: toDTO)
			.toList();
		model.addAttribute("taskList", taskList);
		
		return "tasks/list";
	}
	
	/**
	 * タスク詳細画面
	 * 指定された ID のタスク情報を取得して画面へ渡す
	 */
	@GetMapping("/tasks/{id}")
	public String showDetaul(@PathVariable("id") long taskId, Model model) {

	    // 指定 ID の TaskEntity を Service から取得
	    // 見つからない場合は IllegalArgumentException をスロー（404 の代わり）
	    var taskEntity = taskService.findById(taskId)
	            .orElseThrow(() -> new IllegalArgumentException("Task not found: id = " + taskId));

	    // 画面で利用するため taskId を Model に追加
	    // TaskEntity は record なので id() でアクセスできる
	    model.addAttribute("task", TaskDTO.toDTO(taskEntity));

	    // resources/templates/tasks/detail.html をレンダリング
	    return "tasks/detail";
	}
	
	
	/**
	 * 作成画面への遷移
	 *  Get /tasks/creationForm
	 * @return
	 */
	@GetMapping("/tasks/creationForm")
	public String showCreationForm() {
		return "tasks/form";
	}
	
	// POST /tasks
	/**
	 * 作成のリクエストを受け取るハンドラーメソッド
	 * @param model
	 * @return
	 */
	@PostMapping("/tasks")
	public String create(TaskFrom form, Model model) {
		return list(model);
	}
}
