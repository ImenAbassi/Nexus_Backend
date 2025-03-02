package com.example.nexus.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Repository.UserRepository;
import com.example.nexus.security.JwtTokenUtil;
import com.example.nexus.security.LoginRequest;
import com.example.nexus.security.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            // Fetch the user by CIN (username)
            User user = userRepository.findByCin(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "User not found with username: " + loginRequest.getUsername()));

            // Fetch privileges for the user
            List<Privilege> privileges = user.getUserCompagnes().stream()
                    .map(UserCompagne::getRole)
                    .flatMap(role -> role.getPrivileges().stream())
                    .distinct()
                    .collect(Collectors.toList());

            // Return user, token, and privileges
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", token);
            response.put("privileges", privileges);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtTokenUtil.extractToken(request);
        if (token != null) {
            jwtTokenUtil.validateToken(token); // Invalidate the token
        }
        // Récupérer l'utilisateur via le token
        String username = jwtTokenUtil.extractUsername(token);
        User user = userRepository.findByCin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // handleLogoutPointage(user);
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

   /*  public void handleLoginPointage(User user) {
        LocalDate today = LocalDate.now();
        Pointage pointage = pointageRepository.findByUserAndDatePointage(user, today)
                .orElseGet(() -> createNewPointage(user, today));

        addPointageOperation(pointage, "ENTREE");

    }

   public void handleLogoutPointage(User user) {
        LocalDate today = LocalDate.now();
        Pointage pointage = pointageRepository.findByUserAndDatePointage(user, today).orElse(null);

        if (pointage != null) {
            // Ajouter une opération de sortie
            addPointageOperation(pointage, "SORTIE");
            // Recalculer les heures travaillées
            pointage.calculerHeuresTravaillees();
            pointageRepository.save(pointage);
        }
    }

    private Pointage createNewPointage(User user, LocalDate date) {
        Pointage pointage = new Pointage();
        pointage.setUser(user);
        pointage.setDatePointage(date);
        pointage.setHeuresTravaillees(0L);
        return pointageRepository.save(pointage);
    }

    private void addPointageOperation(Pointage pointage, String type) {
        PointageOperation operation = new PointageOperation();
        operation.setPointage(pointage);
        operation.setHeure(LocalDateTime.now());
        operation.setType(type);
        pointageOperationRepository.save(operation);
    } */
}