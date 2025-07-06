package com.booking.movie.Repository;

import com.booking.movie.Repository.Entity.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEnity, Integer> {
    Optional<UserEnity> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserEnity> findByUsername(String username);
    boolean existsByUsername(String username);
} 