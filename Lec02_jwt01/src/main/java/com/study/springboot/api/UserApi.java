package com.study.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApi {
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/join")
	public String join(@RequestBody JoinRequestDto joinRequestDto) {
		return userService.join(joinRequestDto);
	}
	
	@PostMapping("/api/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }
}
