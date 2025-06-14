package com.build.Filmmoi.service;



import com.build.Filmmoi.dto.ShowtimeDTO;
import com.build.Filmmoi.entity.Showtime;
import com.build.Filmmoi.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing Showtimes.
 */
@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    // Retrieve all showtimes
    public List<ShowtimeDTO> getAllShowtimes() {
        return showtimeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Save or update a showtime
    public Showtime saveShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    // Convert Entity to DTO
    private ShowtimeDTO convertToDTO(Showtime showtime) {
        ShowtimeDTO dto = new ShowtimeDTO();
        dto.setShowtimeId(showtime.getShowtimeId());
        dto.setMovieId(showtime.getMovie().getMovieId());
        dto.setMovieTitle(showtime.getMovie().getTitle());
        dto.setShowtime(showtime.getShowtime());
        dto.setPrice(showtime.getPrice());
        return dto;
    }
}
