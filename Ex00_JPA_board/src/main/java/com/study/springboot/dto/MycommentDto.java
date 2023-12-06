package com.study.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.springboot.entity.Mycomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MycommentDto {
	private Long id;
//	@JsonProperty("article_id")
	private Long articleId;
	private String nickname;
	private String body;
	
	public static MycommentDto createMycommentDto(Mycomment mycomment) {
		return new MycommentDto(
					mycomment.getId(),
					mycomment.getArticle().getId(),
					mycomment.getNickname(),
					mycomment.getBody()
				);
	}
}
