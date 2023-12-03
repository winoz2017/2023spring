package com.study.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	
	public void patch(Article article) {
		 if (article.title != null)
	            this.title = article.title;
	        if (article.content != null)
	            this.content = article.content;
		
	}
	
	
}
