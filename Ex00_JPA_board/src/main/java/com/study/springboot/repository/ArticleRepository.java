package com.study.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.study.springboot.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll(); // Iterable → ArrayList 수정
}

