package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String showDetail(@PathVariable("id") long taskId, Model model) {

	    // 指定 ID の TaskEntity を Service から取得
	    // 見つからない場合は IllegalArgumentException をスロー（404 の代わり）
	    var taskEntity = taskService.findById(taskId)
	            .orElseThrow(TaskNotFoundException::new);

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
	public String showCreationForm(@ModelAttribute TaskForm form) { // 81 @ModelAttributeの追加
		return "tasks/form";
	}
	
	// POST /tasks
	/**
	 * 作成のリクエストを受け取るハンドラーメソッド
	 * @param
	 * @return
	 */
	@PostMapping
	public String create(@Validated TaskForm form, BindingResult bindingResult) {  
		// 79: 以下、空文字で入力した際、再度フォーム画面に戻る分岐処理
		if (bindingResult.hasErrors()) { //  79:バリデーションエラーの確認
			return showCreationForm(form); 
			//80:  タスク作成時のPOSTリクエストで受け取ったタスクフォームを
//			       バリデーションエラー時にクリエーションフォームに渡す
		}
		taskService.create(form.toEntity());
		return "redirect:/tasks";  // 二重サブミット対策
	}

	// 88: タスク編集ページ
	// GET/ tasks/{taskId}/editForm
	@GetMapping("/{id}/editForm")
	public String showEditForm(@PathVariable("id") long id, Model model){
		// 90: タスク編集画面にフォームに入力した値を取得する
		var taskEntity = taskService.findById(id)
						.orElseThrow(TaskNotFoundException::new); // 91: 404エラー：引数のないメソッドの呼び出し時の書き方(メソッド参照)
		var form = TaskForm.formEntity(taskEntity); // 92: リファクタリング entityをformに変換
		model.addAttribute("taskForm", form);
		return "tasks/form";
	}
}
