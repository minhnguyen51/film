package com.booking.movie.Service;

import com.booking.movie.Model.Dto.MovieDTO;
import com.booking.movie.Repository.Entity.MovieEntity;
import com.booking.movie.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    

    //  Lấy tất cả phim

    public List<MovieDTO> findAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            MovieDTO dto = new MovieDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setPoster_url(entity.getPoster_url());
            dto.setRelease_date(entity.getReleaseDate());
            dto.setGenre(entity.getGenre());
            dto.setDirector(entity.getDirector());
            dto.setCast(entity.getCast());
            dtos.add(dto);
        }
        return dtos;
    }


    //  Lấy phim theo ID

    public MovieDTO findMovieById(int id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if (entity.isPresent()) {
            MovieEntity e = entity.get();
            MovieDTO dto = new MovieDTO();
            dto.setId(e.getId());
            dto.setTitle(e.getTitle());
            dto.setDescription(e.getDescription());
            dto.setPoster_url(e.getPoster_url());
            dto.setRelease_date(e.getReleaseDate());
            dto.setGenre(e.getGenre());
            dto.setDirector(e.getDirector());
            dto.setCast(e.getCast());
            return dto;
        }
        return null;
    }



    //  Lấy phim theo thể loại

    public List<MovieEntity> findMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }


    //  Tìm kiếm phim theo tên

    public List<MovieEntity> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }


    //  Thêm phim mới

    public MovieDTO addMovie(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPoster_url(dto.getPoster_url());
        entity.setReleaseDate(dto.getRelease_date());
        entity.setGenre(dto.getGenre());
        entity.setDirector(dto.getDirector());
        entity.setCast(dto.getCast());
        MovieEntity saved = movieRepository.save(entity);
        MovieDTO result = new MovieDTO();
        result.setId(saved.getId());
        result.setTitle(saved.getTitle());
        result.setDescription(saved.getDescription());
        result.setPoster_url(saved.getPoster_url());
        result.setRelease_date(saved.getReleaseDate());
        result.setGenre(saved.getGenre());
        result.setDirector(saved.getDirector());
        result.setCast(saved.getCast());
        return result;
    }


     // Cập nhật thông tin phim

    public MovieDTO updateMovie(int id, MovieDTO dto) {
        Optional<MovieEntity> opt = movieRepository.findById(id);
        if (opt.isPresent()) {
            MovieEntity entity = opt.get();
            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity.setPoster_url(dto.getPoster_url());
            entity.setReleaseDate(dto.getRelease_date());
            entity.setGenre(dto.getGenre());
            entity.setDirector(dto.getDirector());
            entity.setCast(dto.getCast());
            MovieEntity saved = movieRepository.save(entity);
            MovieDTO result = new MovieDTO();
            result.setId(saved.getId());
            result.setTitle(saved.getTitle());
            result.setDescription(saved.getDescription());
            result.setPoster_url(saved.getPoster_url());
            result.setRelease_date(saved.getReleaseDate());
            result.setGenre(saved.getGenre());
            result.setDirector(saved.getDirector());
            result.setCast(saved.getCast());
            return result;
        }
        return null;
    }


     //Xóa phim (soft delete - chỉ thay đổi status)

    public boolean deleteMovie(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
