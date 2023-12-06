package com.study.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.dto.MycommentDto;
import com.study.springboot.service.MycommentService;

@RestController
public class MycommentApiController {
	@Autowired
	private MycommentService mycommentService;
	
	@GetMapping("/api/articles/{articleId}/comments")
	@CrossOrigin
	public ResponseEntity<List<MycommentDto>> mycomments(@PathVariable Long articleId){
		//서비스위임
		List<MycommentDto> dtos = mycommentService.mycomments(articleId);
		//결과응답
		if (dtos.isEmpty()) {
	        // 데이터가 없을 때 에러 응답
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } else {
	        // 데이터가 있을 때 성공 응답
	        return ResponseEntity.status(HttpStatus.OK).body(dtos);
	    }
	}
	
	@PostMapping("/api/articles/{articleId}/comments")
	@CrossOrigin
	public ResponseEntity<MycommentDto> create(
			@PathVariable Long articleId,
			@RequestBody MycommentDto dto){
		//서비스의 위임
		MycommentDto createdDto = mycommentService.create(articleId,dto);
		//결과응답

		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}
	
	@PatchMapping("/api/comments/{id}")
	@CrossOrigin
	public ResponseEntity<MycommentDto> update(
			@PathVariable Long id,
			@RequestBody MycommentDto dto
			){
		//서비스에게 위임
		MycommentDto updatedDto = mycommentService.update(id,dto);
		
		//결과응답
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}
			
	@DeleteMapping("/api/comments/{id}")
	@CrossOrigin
	public ResponseEntity<MycommentDto> delete(@PathVariable Long id){
		
		//서비스에게 위임
		MycommentDto deletedDto = mycommentService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}
	
}
