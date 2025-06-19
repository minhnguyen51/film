# Hướng dẫn sử dụng MovieService và ScheduleService

## 🎬 MovieService - Quản lý phim

### Các chức năng chính:

#### 1. **Lấy danh sách phim**
```java
// Lấy tất cả phim
List<MovieDTO> allMovies = movieService.findAllMovies();

// Lấy phim theo ID
MovieDTO movie = movieService.findMovieById(1);

// Lấy phim theo trạng thái
List<MovieDTO> activeMovies = movieService.findMoviesByStatus("ACTIVE");

// Lấy phim theo thể loại
List<MovieDTO> actionMovies = movieService.findMoviesByGenre("Action");

// Tìm kiếm phim theo tên
List<MovieDTO> searchResults = movieService.searchMoviesByTitle("Avengers");

// Lấy phim đang chiếu
List<MovieDTO> showingMovies = movieService.findCurrentlyShowingMovies();

// Lấy phim sắp chiếu
List<MovieDTO> upcomingMovies = movieService.findUpcomingMovies();
```

#### 2. **Thêm phim mới**
```java
MovieDTO newMovie = new MovieDTO();
newMovie.setTitle("Avengers: Endgame");
newMovie.setDescription("Phim siêu anh hùng");
newMovie.setGenre("Action");
newMovie.setDirector("Russo Brothers");
newMovie.setDuration(181);
// ... set các trường khác

MovieDTO savedMovie = movieService.addMovie(newMovie);
```

#### 3. **Cập nhật phim**
```java
MovieDTO updateData = new MovieDTO();
updateData.setTitle("Avengers: Endgame (Updated)");
updateData.setDescription("Mô tả mới");

MovieDTO updatedMovie = movieService.updateMovie(1, updateData);
```

#### 4. **Xóa phim (Soft Delete)**
```java
boolean deleted = movieService.deleteMovie(1); // Chỉ thay đổi status thành INACTIVE
```

## 🕐 ScheduleService - Quản lý lịch chiếu

### Các chức năng chính:

#### 1. **Lấy danh sách lịch chiếu**
```java
// Lấy tất cả lịch chiếu
List<ScheduleDTO> allSchedules = scheduleService.findAllSchedules();

// Lấy lịch chiếu theo ID
ScheduleDTO schedule = scheduleService.findScheduleById(1);

// Lấy lịch chiếu theo phim
List<ScheduleDTO> movieSchedules = scheduleService.findSchedulesByMovie(1);

// Lấy lịch chiếu theo rạp
List<ScheduleDTO> cinemaSchedules = scheduleService.findSchedulesByCinema(1);

// Lấy lịch chiếu theo phòng
List<ScheduleDTO> roomSchedules = scheduleService.findSchedulesByRoom(1);

// Lấy lịch chiếu theo ngày
LocalDateTime date = LocalDateTime.now();
List<ScheduleDTO> daySchedules = scheduleService.findSchedulesByDate(date);

// Lấy lịch chiếu theo khoảng thời gian
LocalDateTime startDate = LocalDateTime.now();
LocalDateTime endDate = LocalDateTime.now().plusDays(7);
List<ScheduleDTO> rangeSchedules = scheduleService.findSchedulesByDateRange(startDate, endDate);

// Lấy lịch chiếu theo phim và ngày
List<ScheduleDTO> movieDaySchedules = scheduleService.findSchedulesByMovieAndDate(1, date);

// Lấy lịch chiếu theo trạng thái
List<ScheduleDTO> activeSchedules = scheduleService.findSchedulesByStatus("ACTIVE");
```

#### 2. **Thêm lịch chiếu mới**
```java
ScheduleDTO newSchedule = new ScheduleDTO();
newSchedule.setMovieId(1);
newSchedule.setRoomId(1);
newSchedule.setCinemaId(1);
newSchedule.setStartTime(LocalDateTime.now().plusDays(1).withHour(20).withMinute(0));
newSchedule.setEndTime(LocalDateTime.now().plusDays(1).withHour(23).withMinute(0));
newSchedule.setPrice(120000.0);

ScheduleDTO savedSchedule = scheduleService.addSchedule(newSchedule);
```

#### 3. **Cập nhật lịch chiếu**
```java
ScheduleDTO updateData = new ScheduleDTO();
updateData.setStartTime(LocalDateTime.now().plusDays(2).withHour(21).withMinute(0));
updateData.setPrice(150000.0);

ScheduleDTO updatedSchedule = scheduleService.updateSchedule(1, updateData);
```

#### 4. **Hủy lịch chiếu**
```java
boolean cancelled = scheduleService.cancelSchedule(1); // Thay đổi status thành CANCELLED
```

## 🔧 Cấu trúc Database

### Bảng `movies`:
- `id` (Primary Key)
- `title` (Tên phim)
- `description` (Mô tả)
- `poster_url` (URL poster)
- `release_date` (Ngày phát hành)
- `genre` (Thể loại)
- `director` (Đạo diễn)
- `cast` (Diễn viên)
- `duration` (Thời lượng - phút)
- `status` (Trạng thái: ACTIVE/INACTIVE)

### Bảng `schedules`:
- `id` (Primary Key)
- `movie_id` (Foreign Key -> movies)
- `room_id` (Foreign Key -> rooms)
- `cinema_id` (Foreign Key -> cinemas)
- `start_time` (Thời gian bắt đầu)
- `end_time` (Thời gian kết thúc)
- `price` (Giá vé)
- `status` (Trạng thái: ACTIVE/CANCELLED/COMPLETED)

### Bảng `cinemas`:
- `id` (Primary Key)
- `name` (Tên rạp)
- `address` (Địa chỉ)
- `phone` (Số điện thoại)
- `status` (Trạng thái: ACTIVE/INACTIVE)

### Bảng `rooms`:
- `id` (Primary Key)
- `name` (Tên phòng)
- `capacity` (Sức chứa)
- `cinema_id` (Foreign Key -> cinemas)
- `status` (Trạng thái: ACTIVE/INACTIVE/MAINTENANCE)

## ⚠️ Lưu ý quan trọng:

1. **Xung đột lịch chiếu**: Khi thêm lịch chiếu mới, hệ thống sẽ tự động kiểm tra xung đột với các lịch chiếu khác trong cùng phòng.

2. **Soft Delete**: Khi xóa phim, hệ thống chỉ thay đổi status thành INACTIVE, không xóa dữ liệu thực sự.

3. **Validation**: Cần thêm validation cho các trường bắt buộc trước khi sử dụng trong production.

4. **Error Handling**: Cần thêm exception handling phù hợp cho các trường hợp lỗi.

## 🚀 Sử dụng trong Controller:

```java
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.findAllMovies();
    }
    
    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable int id) {
        return movieService.findMovieById(id);
    }
    
    @PostMapping
    public MovieDTO addMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }
    
    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable int id, @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(id, movieDTO);
    }
    
    @DeleteMapping("/{id}")
    public boolean deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }
} 