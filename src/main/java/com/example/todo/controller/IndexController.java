package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class IndexController {

	// http:localhost:8080 -> "hello World"
	// GET / -> "Hello World"
	@GetMapping
	@ResponseBody
	public String index() {
		return "Hello World";
	}
}
