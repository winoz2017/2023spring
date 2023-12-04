package com.study.springboot.dto;

import com.study.springboot.entity.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	
	private Long id;
	private String title;
	private boolean done;
	
	public TodoDTO(TodoEntity entity) {
		this.id = entity.getId();
	    this.title = entity.getTitle();
	    this.done = entity.isDone();
	}

	public static TodoEntity toEntity(TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
	
}
