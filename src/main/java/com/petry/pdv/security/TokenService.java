package com.petry.pdv.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.petry.pdv.login.entity.Login;

@Service
public class TokenService {
	
//	@Value("${api.security.token.secret}")
//	private String secret;
	
	public String generateToken(Login login) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("ALFPSSSSPAAKUJWESFZXX");
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(login.getUsername())
					.withExpiresAt(generateExpirationDate())
					.sign(algorithm);
			return token;

		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token JWT !", e);
		}		
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("ALFPSSSSPAAKUJWESFZXX");
			return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
