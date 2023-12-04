package com.study.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.springboot.dto.ResponseDTO;
import com.study.springboot.dto.TodoDTO;
import com.study.springboot.entity.TodoEntity;
import com.study.springboot.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	private TodoService service;
	
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		List<String> list = new ArrayList<String>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/api/todo")
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporay-user";
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		List<TodoDTO> dtos = entities.stream()
				.map(entity -> new TodoDTO(entity)).collect(Collectors.toList());
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	
}
