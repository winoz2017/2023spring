package com.study.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.service.RestaurantService;
import com.study.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;


@RestController
//@SecurityRequirement(name = "Bearer Authentication")
public class UserApi {
	@Autowired
	private  UserService userService;
	
	
	 @Operation(
			    summary = "회원가입",
			    description = "회원가입"
			)
	@PostMapping("/api/join")
	 public String join(@RequestBody JoinRequestDto joinRequestDto) {
		 System.out.println("Received request in UserApi: " + joinRequestDto);
        return userService.join(joinRequestDto);
    }
	 
	 @PostMapping("/api/login")
	    public String login(@RequestBody LoginRequestDto loginRequestDto) {
	        return userService.login(loginRequestDto);
	    }
}
