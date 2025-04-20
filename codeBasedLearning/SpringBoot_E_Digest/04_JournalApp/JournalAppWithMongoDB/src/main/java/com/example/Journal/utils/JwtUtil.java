package com.example.Journal.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // should be 256-bit

	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 5 minutes expiration time
	
	
	/* TOken generation process
	 * Start    --->
	 */
	  public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }
	  
	  private String createToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .claims(claims)
	                .subject(subject)
	                .header().empty().add("typ","JWT")
	                .and()
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 5 minutes expiration time
	                .signWith(getSigningKey())
	                .compact();
	    }
	  
	  private SecretKey getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    }
	  
	  
	  /* TOken generation process
		 * End    --->
		 */
	  
	  /* TOken Validation process
			 * Start    --->
			 */
	  
	  public Boolean validateToken(String token) {
	        return !isTokenExpired(token);
	    }
	  
	  private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	  
	  public Date extractExpiration(String token) {
	        return extractAllClaims(token).getExpiration();
	    }
	  
	  private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getSigningKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
	  
	  
	  /* TOken Validation process
		 * Start    --->
		 */
	  
	  public String extractUsername(String token) {
	        Claims claims = extractAllClaims(token);
	        return claims.getSubject();
	    }

}
