package com.study.springboot.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.JoinRequestDto;
import com.study.springboot.api.request.LoginRequestDto;
import com.study.springboot.entity.UserEntity;
import com.study.springboot.repository.UserRepository;
import com.study.springboot.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	public String join(JoinRequestDto joinRequestDto) {

		if (joinRequestDto.getPassword() == null) {
			// Handle the case where the password is null (throw an exception, return an
			// error message, etc.)
			return "비밀번호를 입력하세요";
		}
		UserEntity user = new UserEntity();
		user.setEmail(joinRequestDto.getEmail());
		user.setUsername(joinRequestDto.getUsername());
		user.setCreatedAt(ZonedDateTime.now());
		user.setUpdatedAt(ZonedDateTime.now());
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
