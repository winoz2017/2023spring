package com.study.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "User API")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/api/join")
    @ApiOperation(value = "Join a new user", notes = "Creates a new user account based on the provided information")
    public String join(@RequestBody JoinRequestDto joinRequestDto) {
        return userService.join(joinRequestDto);
    }

    @PostMapping("/api/login")
    @ApiOperation(value = "User login", notes = "Logs in a user based on the provided login information")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }
}
