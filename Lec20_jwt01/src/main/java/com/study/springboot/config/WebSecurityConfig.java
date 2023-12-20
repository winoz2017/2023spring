package com.study.springboot.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.study.springboot.repository.UserRepository;
import com.study.springboot.security.JwtProvider;
import com.study.springboot.security.JwtTokenFilter;
import com.study.springboot.service.UserService;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	
	@Value("${jwt.secret}")
    private String secretKey;
	
	@Bean
    public JwtProvider jwtTokenProvider() {
        return new JwtProvider();
    }
    
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        		.cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable() // http의 기본 인증. ID, PW 인증방식
                .authorizeHttpRequests()
                .requestMatchers("/status", "/v2/api-docs", "/swagger-ui/**", "/images/**", "/api/join", "/api/login").permitAll()
                .requestMatchers("/restaurants").authenticated()
                .anyRequest().permitAll().and()
                .addFilterBefore(new JwtTokenFilter(secretKey), UsernamePasswordAuthenticationFilter.class);

            return http.build();

  }

	
}
