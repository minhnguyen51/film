package com.build.Filmmoi.repository;



import com.build.Filmmoi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Movies.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
