package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class QuerydslConfig {
	 private final EntityManager em;
    

    @Bean
    public JPAQueryFactory querFactory() {
    	return new JPAQueryFactory(em);
    }
}
