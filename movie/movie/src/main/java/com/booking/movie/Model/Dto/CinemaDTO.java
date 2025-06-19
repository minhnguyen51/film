package com.booking.movie.Model.Dto;

import lombok.Data;

@Data
public class CinemaDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String status; // ACTIVE, INACTIVE
}
