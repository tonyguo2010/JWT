package com.seakie.JWT;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTToken {

	final public static Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public static String generateUserToken() {
	    List<String> roles = List.of("ROLE_USER");

	    return Jwts.builder()
	            .setSubject("User")
	            .claim("roles", roles) // Critical: Add roles to the JWT
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h expiry
	            .signWith(secret)
	            .compact();
	}
	
	public static Authentication parseToken(String token) {
		Claims claims =
				Jwts.parserBuilder()
					.setSigningKey(secret)
					.build()
					.parseClaimsJws(token)
					.getBody();
		System.out.println(claims.toString());
		
		String subject = claims.getSubject();
		List<String> roles = claims.get("roles", List.class);
		List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
		
		return new UsernamePasswordAuthenticationToken(subject, null, authorities);
	}

	public static String generateAdminToken() {
	    List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN");

	    return Jwts.builder()
	            .setSubject("Admin")
	            .claim("roles", roles) // Critical: Add roles to the JWT
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h expiry
	            .signWith(secret)
	            .compact();
	    }
}
