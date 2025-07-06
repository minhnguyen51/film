package com.booking.movie.Controller;

import com.booking.movie.Model.Dto.UserRegisterDTO;
import com.booking.movie.Model.Dto.UserLoginDTO;
import com.booking.movie.Model.Dto.UserResponseDTO;
import com.booking.movie.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
} 