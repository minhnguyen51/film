package com.booking.movie.Repository;

import com.booking.movie.Repository.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    // Tìm phim theo thể loại
    List<MovieEntity> findByGenre(String genre);
    // Tìm phim theo đạo diễn
    List<MovieEntity> findByDirector(String director);
    // Tìm phim theo tên (tìm kiếm mờ)
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);
    // Tìm phim theo ngày phát hành
    List<MovieEntity> findByReleaseDate(LocalDate releaseDate);
    // Tìm phim theo khoảng thời gian phát hành
    List<MovieEntity> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
    // viết sql tự do như này

//    @Query(value = "SELECT SUM(actual_m_ut_checklist_number) FROM actual_detail_requirement "
//            + "WHERE actual_header_id = :actualHeaderId", nativeQuery = true)
//    Double getTotalMUtChecklistNumberFroActualHeaderId(@Param("actualHeaderId") Long etHeaderId);

}
