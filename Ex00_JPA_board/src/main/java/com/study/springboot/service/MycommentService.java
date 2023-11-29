package com.study.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dto.MycommentDto;
import com.study.springboot.entity.Mycomment;
import com.study.springboot.repository.ArticleRepository;
import com.study.springboot.repository.MycommentRepository;

@Service
public class MycommentService {
	@Autowired
	private MycommentRepository mycommentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<MycommentDto> comments(Long articleId) {
       
        List<Mycomment> mycomments = mycommentRepository.findByArticleId(articleId);

        
        List<MycommentDto> dtos = new ArrayList<MycommentDto>();
        for (int i = 0; i < mycomments.size(); i++) {
        	Mycomment c = mycomments.get(i);
        	MycommentDto dto = MycommentDto.createMycommentDto(c);
            dtos.add(dto);
        }

        // 3. 결과 반환
        return mycommentRepository.findByArticleId(articleId)
        		.stream()
        		.map(mycomment -> MycommentDto.createMycommentDto(mycomment))
				.collect(Collectors,toList());
    }
}
