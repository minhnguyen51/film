package com.booking.movie.Service;

import com.booking.movie.Model.Dto.UserRegisterDTO;
import com.booking.movie.Model.Dto.UserLoginDTO;
import com.booking.movie.Model.Dto.UserResponseDTO;
import com.booking.movie.Repository.Entity.UserEnity;
import com.booking.movie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> register(UserRegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        UserEnity user = new UserEnity();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole() != null ? registerDTO.getRole() : "USER");
        userRepository.save(user);
        return ResponseEntity.ok("Register success");
    }

    public ResponseEntity<?> login(UserLoginDTO loginDTO) {
        Optional<UserEnity> userOpt = userRepository.findByUsername(loginDTO.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        UserEnity user = userOpt.get();
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return ResponseEntity.ok(response);
    }
} 