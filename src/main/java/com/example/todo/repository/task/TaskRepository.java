package com.example.todo.repository.task;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.todo.service.task.TaskEntity;

@Mapper
public interface TaskRepository {
	@Select("SELECT id, summary, description, status FROM tasks;")
	List<TaskEntity> select();
}
