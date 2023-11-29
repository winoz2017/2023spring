package com.study.springboot.dto;

import com.study.springboot.entity.Mycomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드를 파라미터로 갖는 생성자 자동 생성
@NoArgsConstructor // 파라미터가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성

public class MycommentDto {
	 private Long id; // 댓글의 id
	    private Long articleId; // 댓글의 부모 id
	    private String nickname; // 댓글 작성자
	    private String body; // 댓글 본문

		 public static MycommentDto createMycommentDto(Mycomment mycomment) {
		        return new MycommentDto(
		        		mycomment.getId(), // 댓글 엔티티의 id
		        		mycomment.getArticle().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
		        		mycomment.getNickname(), // 댓글 엔티티의 nickname
		        		mycomment.getBody() // 댓글 엔티티의 body
		        );
		    }
}
