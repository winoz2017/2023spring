package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.TodoEntity;
import com.study.springboot.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("my first todo item").build();
		todoRepository.save(entity);
		TodoEntity saveEntity = todoRepository.findById(entity.getId()).get();
		return saveEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity) {
		validate(entity);
		todoRepository.save(entity);
		log.info("entity id : {} is saved.", entity.getId());
		return todoRepository.findByUserId(entity.getUserId());
	}

	private void validate(TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null.");
		}
		if(entity.getUserId() == null) {
			log.warn("unknown user");
			throw new RuntimeException("unknown user.");
		}
		
	}
	
	public List<TodoEntity> retrieve(String userId){
		return todoRepository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(TodoEntity entity){
		validate(entity);
		final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
		original.ifPresent(todo ->{
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			todoRepository.save(todo);
		});
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		validate(entity);
		try {
			todoRepository.delete(entity);
		} catch (Exception e) {
			log.error("error deleting entity", entity.getId(),e);
			throw new RuntimeException("error deleting entity" + entity.getId());
		}
		return retrieve(entity.getUserId());
	}
	
}
