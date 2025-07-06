package com.booking.movie.Model.Dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private String role;
} 