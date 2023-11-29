package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.Mycomment;

@Repository
public interface MycommentRepository extends JpaRepository<Mycomment, Long> {
	 @Query(value = "SELECT * FROM comment WHERE article_id = :articleId",
	            nativeQuery = true) // value 속성에 실행하려는 쿼리 작성
	   
	List<Mycomment> findByArticleId(Long articleId);
	 List<Mycomment> findByNickname(String nickname);
}
