package com.example.nexus.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.nexus.security.JwtTokenUtil;
import com.example.nexus.security.LoginRequest;
import com.example.nexus.security.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // Ajouter CORS ici aussi
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        // Return user and token
        Map<String, Object> response = new HashMap<>();
        response.put("user", userDetails);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Invalidate the token (optional, depends on your requirements)
        String token = jwtTokenUtil.extractToken(request);
        if (token != null) {
            // You can add the token to a blacklist or perform other actions to invalidate it
        }

        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/verifyToken")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        String token = jwtTokenUtil.extractToken(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = jwtTokenUtil.extractToken(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
            String username = claims.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newToken = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(newToken);
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

}
