package com.example.todo.controller.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 91 存在しないタスクIDの編集ページが呼ばれたときは404エラーの表示
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException{

}
