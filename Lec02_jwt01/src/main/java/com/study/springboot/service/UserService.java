package com.study.springboot.service;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.entity.UserEntity;
import com.study.springboot.repository.UserRepository;
import com.study.springboot.security.JwtProvider;

@Service
public class UserService {
	
	
	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private  UserRepository userRepository;
	 @Autowired
	    private JwtProvider jwtProvider;
	
	public String join(JoinRequestDto joinRequestDto) {
		UserEntity user = new UserEntity();
		 user.setEmail(joinRequestDto.getEmail());
	        user.setUsername(joinRequestDto.getUsername());
	        user.setCreatedAt(ZonedDateTime.now());
	        user.setRole("user");
	        
	        
	        // Encode the password before saving it
	        String encodedPassword = passwordEncoder.encode(joinRequestDto.getPassword());
	        user.setPassword(encodedPassword);

	        // Save the user using the repository
	        userRepository.save(user);
	        
	        // You can return a success message or perform other actions as needed
	        return "입력완료";
	}
	public String login(LoginRequestDto loginRequestDto) {
    	String email = loginRequestDto.getEmail();
        String rawPassword = loginRequestDto.getPassword();

        UserEntity byEmail = userRepository.findByEmail(email);

        // 비밀번호 일치 여부 확인
        if(passwordEncoder.matches(rawPassword, byEmail.getPassword())){

            // JWT 토큰 반환
            String jwtToken = jwtProvider.generateJwtToken(
            		byEmail.getId(), byEmail.getEmail(), byEmail.getUsername(),
            		byEmail.getRole()
            		);

			return "로그인 성공 " + jwtToken;
        }

        return "로그인 실패";
    }


	
		
}
