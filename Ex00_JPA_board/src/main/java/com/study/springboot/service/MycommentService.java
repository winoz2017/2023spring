package com.study.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dto.MycommentDto;
import com.study.springboot.entity.Article;
import com.study.springboot.entity.Mycomment;
import com.study.springboot.repository.ArticleRepository;
import com.study.springboot.repository.MycommentRepository;

import jakarta.transaction.Transactional;

@Service
public class MycommentService {
	@Autowired
	private MycommentRepository mycommentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	public List<MycommentDto> mycomments(Long articleId) {
//		//댓글조회
//		List<Mycomment> mycomments = mycommentRepository.findByArticleId(articleId);
//		//엔티티 -> DTO
//		List<MycommentDto> dtos = new ArrayList<MycommentDto>();
//		for(int i=0;i<mycomments.size();i++) {
//			Mycomment c = mycomments.get(i);
//			MycommentDto dto = MycommentDto.createMycommentDto(c);
//			dtos.add(dto);
//		}
		
		return mycommentRepository.findByArticleId(articleId)
				.stream()  // 댓글 엔티티 목록을 스트림으로 변환
				.map(mycomment -> MycommentDto.createMycommentDto(mycomment)) // 엔티티를 DTO로 맵핑
				.collect(Collectors.toList());		
				//스트림 데이타를 리스트 자료셩으로 변환

		//결과반환
//		return dtos;
	}

	@Transactional
	public  MycommentDto create(Long articleId, MycommentDto dto) {

		//1. 게시글 조회 및 예외발생
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" ));
		//2. 댓글 엔티티 생성
		Mycomment mycomment = Mycomment.createMycomment(dto, article);
		//3. 댓글 엔티티를 DB에 저장
		Mycomment created = mycommentRepository.save(mycomment);
		//4. DTO로 변환해 반환
		return MycommentDto.createMycommentDto(mycomment);
	}

	public MycommentDto update(Long id, MycommentDto dto) {
		//1. 댓글 조회 및 예외 발생
		Mycomment target = mycommentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!"));
		//2. 댓글 수정
		target.patch(dto);
		//3. DB로 갱신
		Mycomment updated = mycommentRepository.save(target);
		//4. 댓글 엔티티를 DTO로 변환 및 반환
		return MycommentDto.createMycommentDto(updated);
	}

	@Transactional
	public MycommentDto delete(Long id) {
		//1. 댇글 조회 및 예외 발생
		Mycomment target = mycommentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("댓글 삭제 실패 : 대상이 없습니다."));
		//2. 댓글 삭제
		mycommentRepository.delete(target);
		
		//3. 삭제 댓글을 DTO로 변환
		return MycommentDto.createMycommentDto(target);
	}
	
	
}
