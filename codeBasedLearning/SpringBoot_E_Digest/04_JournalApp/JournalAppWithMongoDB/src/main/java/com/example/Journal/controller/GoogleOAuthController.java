package com.example.Journal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepository;
import com.example.Journal.service.UserDetailsServiceImpl;
import com.example.Journal.utils.JwtUtil;

@RestController
@RequestMapping("/auth/google")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class GoogleOAuthController {


    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // This endpoint will be called by Swagger UI for the token exchange
    @PostMapping("/callback")
    public ResponseEntity<?> handleTokenExchange(
            @RequestParam("grant_type") String grantType,
            @RequestParam("code") String code,
            @RequestParam(value = "redirect_uri", required = false) String redirectUri,
            @RequestParam(value = "client_id", required = false) String clientIdParam) {
        
        try {
            log.info("Token exchange request - Grant Type: {}, Code: {}", grantType, code);
            
            if (!"authorization_code".equals(grantType)) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "unsupported_grant_type"));
            }

            // Exchange code with Google
            String tokenEndpoint = "https://oauth2.googleapis.com/token";
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("code", code);
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);
            params.add("redirect_uri", redirectUri != null ? redirectUri : "http://localhost:8282/swagger-ui/oauth2-redirect.html");
            params.add("grant_type", "authorization_code");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            log.info("Google token response status: {}", tokenResponse.getStatusCode());
            
            if (!tokenResponse.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to get token from Google: {}", tokenResponse.getBody());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "invalid_grant"));
            }

            String accessToken = (String) tokenResponse.getBody().get("access_token");
            
            // Get user info from Google
            String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
            ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);
            
            if (!userInfoResponse.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to get user info from Google");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "invalid_token"));
            }

            Map<String, Object> userInfo = userInfoResponse.getBody();
            String email = (String) userInfo.get("email");
            log.info("User email from Google: {}", email);
            
            // Find or create user
            UserDetails userDetails = null;
            try {
                userDetails = userDetailsService.loadUserByUsername(email);
                log.info("Existing user found: {}", email);
            } catch (Exception e) {
                log.info("Creating new user: {}", email);
                User user = new User();
                user.setEmail(email);
                user.setUserName(email);
                user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                user.setRoles(Arrays.asList("USER"));
                userRepository.save(user);
                userDetails = userDetailsService.loadUserByUsername(email);
            }
             
            // Set authentication context
            var auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            // Generate JWT token
            String jwtToken = jwtUtil.generateToken(email);
            log.info("Generated JWT token for user: {}", email);
            
            // Return OAuth2 standard token response
            Map<String, Object> response = new HashMap<>();
            response.put("access_token", jwtToken);
            response.put("token_type", "Bearer");
            response.put("expires_in", 3600);
            response.put("scope", "openid email profile");

            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Exception during token exchange: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "server_error", "error_description", e.getMessage()));
        }
    }
}
