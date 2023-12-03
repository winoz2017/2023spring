package com.study.springboot.entity;



import com.study.springboot.dto.MycommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mycomment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "article_id", referencedColumnName = "id")
	private Article article;
	@Column
	private String nickname;
	@Column
	private String body;
	
	public static Mycomment createMycomment(MycommentDto dto, Article article) {
		//예외발생
		if(dto.getId() != null )
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 없어야 합니다.");
		if(dto.getArticleId() != article.getId())
			throw new IllegalArgumentException("댁글 생성 실패! 게시글의 ID가 잘못됐습니다.");
		//엔티티 생성 및 반환
		return new Mycomment(
				dto.getId(),
				article,
				dto.getNickname(),
				dto.getBody()
				);
	}

	public void patch(MycommentDto dto) {
		// 예외 발생
		if(this.id != dto.getId())
			throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
		//객체갱신
		if(dto.getNickname() !=null) //수정할 닉네임 데이터가 있다면
			this.nickname = dto.getNickname(); //내용을 반영
		if(dto.getBody() != null) // 수정할 본문 데이터가 있다면
			this.body = dto.getBody(); // 내용을 반영
	}
	

}
