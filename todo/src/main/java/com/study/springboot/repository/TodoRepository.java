package com.study.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
	List<TodoEntity> findByUserId(String userId);
	
//	@Query("select t from todoentity t where t.userid = ?1")
//	TodoEntity findByUserIdQuery(String userId);
}
