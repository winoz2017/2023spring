package com.study.springboot.dto;

import com.study.springboot.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
public class ArticleForm {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
//        return new Article(null, title, content);
        return new Article(id, title, content);
    }
}