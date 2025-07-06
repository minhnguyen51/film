package com.booking.movie.Controller;

import com.booking.movie.Model.Dto.ScheduleDTO;
import com.booking.movie.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.findScheduleById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> addSchedule(@RequestBody ScheduleDTO dto) {
        return ResponseEntity.ok(scheduleService.addSchedule(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable int id, @RequestBody ScheduleDTO dto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSchedule(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(id));
    }
} 