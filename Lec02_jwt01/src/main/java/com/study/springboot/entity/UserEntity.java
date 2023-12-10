package com.study.springboot.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
	@SequenceGenerator(name="user_seq",sequenceName = "user_SEQ",allocationSize = 1)
	private Long id; // 유저에게 고유하게 부여되는 id.
	private String username; // 아이디로 사용할 유저네임. 이메일일 수도 그냥 문자열일 수도 있다.
	private String email;
	private String password; // 패스워드.
	private String role; // 유저의 롤 어드민, 일반사용자
	private String authProvider; // 이후 oauth의 사용할 정보제공자
	private ZonedDateTime createdAt;
}
