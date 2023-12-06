package com.study.springboot.security;



import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


import org.springframework.stereotype.Service;

import com.study.springboot.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	  private static final String SECRET_KEY = "FlRpX30pMqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M1Oicegkpi46AdvrcX1E6CmTUBc6IFbTPiD";

	  public String create(UserEntity userEntity) {
	    // 기한 지금으로부터 1일로 설정
	    Date expiryDate = Date.from(
	        Instant.now()
	            .plus(1, ChronoUnit.DAYS));

//	    String userId = userEntity.getId() != null ? userEntity.getId().toString() : "defaultUserId";
	    // JWT Token 생성
	    return Jwts.builder()
	        .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
	        .setSubject(userEntity.getId().toString()) // sub
	        .setIssuer("demo app") // iss
	        .setIssuedAt(new Date()) // iat
	        .setExpiration(expiryDate) // exp
	        .compact();
	  }


	  public String validateAndGetUserId(String token) {

	    Claims claims = Jwts.parser()
	        .setSigningKey(SECRET_KEY)
	        .parseClaimsJws(token)
	        .getBody();

	    return claims.getSubject();
	  }


	}