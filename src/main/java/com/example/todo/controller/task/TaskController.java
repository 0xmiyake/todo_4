package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.service.task.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // 39: Lombockを使ってボイラープレートコードの削減
@RequestMapping("/tasks") // 78: パス指定の繰り返しを減らす

public class TaskController {
	private final TaskService taskService;

	
	@GetMapping
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
	@GetMapping("/{id}")
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
	@GetMapping("/creationForm")
	public String showCreationForm() {
		return "tasks/form";
	}
	
	// POST /tasks
	/**
	 * 作成のリクエストを受け取るハンドラーメソッド
	 * @param model
	 * @return
	 */
	@PostMapping
	public String create(@Validated TaskForm form, BindingResult bindingResult) {  
		// 79: 以下、空文字で入力した際、再度フォーム画面に戻る分岐処理
		if (bindingResult.hasErrors()) { //  79:バリデーションエラーの確認
			return "tasks/form"; // 79: form.htmlを指している
		}
		taskService.create(form.toEntity());
		return "redirect:/tasks";  // 二重サブミット対策
	}
}
