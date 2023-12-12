package com.study.springboot.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_table_seq")
	@SequenceGenerator(name = "user_table_seq", sequenceName = "user_table_SEQ", allocationSize = 1)
	private Long id;
	private String username; // 아이디로 사용할 유저네임. 이메일일 수도 그냥 문자열일 수도 있다.
	private String email; // 이메일
	private String password; // 패스워드.
	private String role; // 유저의 롤.
	private String authProvider; // example : facebook
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
