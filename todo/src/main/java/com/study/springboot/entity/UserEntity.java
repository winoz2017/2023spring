package com.study.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = "username")}) //특정 사용자 이름이 한 번만 나타날 수 있도록 제한
@Entity
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_SEQ", allocationSize = 1)
	private Long id;
	@Column(nullable = false)
	private String username;
	private String password;
	private String role;
	private String authProvider;
	
	
}
