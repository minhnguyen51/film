package com.booking.movie.Controller;

import com.booking.movie.Model.Dto.MovieDTO;
import com.booking.movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    // Lấy danh sách phim
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    // Lấy chi tiết phim
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    // Thêm phim
    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }

    // Cập nhật phim
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable int id, @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }

    // Xóa phim
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable int id) {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}
