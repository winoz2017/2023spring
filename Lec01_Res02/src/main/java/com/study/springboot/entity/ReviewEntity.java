package com.study.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@Entity
public class ReviewEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "review_SEQ", allocationSize = 1)
	private Long id;
    
    private Long restaurantId;
    private String content;
    private Double score;
    private ZonedDateTime createdAt;
}
