package com.booking.movie.Service;

import com.booking.movie.Model.Dto.ScheduleDTO;
import com.booking.movie.Repository.Entity.ScheduleEntity;
import com.booking.movie.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<ScheduleDTO> findAllSchedules() {
        List<ScheduleEntity> entities = scheduleRepository.findAll();
        List<ScheduleDTO> dtos = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    public ScheduleDTO findScheduleById(int id) {
        Optional<ScheduleEntity> entity = scheduleRepository.findById(id);
        return entity.map(this::toDTO).orElse(null);
    }

    public ScheduleDTO addSchedule(ScheduleDTO dto) {
        ScheduleEntity entity = toEntity(dto);
        ScheduleEntity saved = scheduleRepository.save(entity);
        return toDTO(saved);
    }

    public ScheduleDTO updateSchedule(int id, ScheduleDTO dto) {
        Optional<ScheduleEntity> opt = scheduleRepository.findById(id);
        if (opt.isPresent()) {
            ScheduleEntity entity = opt.get();
            entity.setMovieId(dto.getMovieId());
            entity.setRoomId(dto.getRoomId());
            entity.setCinemaId(dto.getCinemaId());
            entity.setStartTime(dto.getStartTime());
            entity.setEndTime(dto.getEndTime());
            entity.setPrice(dto.getPrice());
            ScheduleEntity saved = scheduleRepository.save(entity);
            return toDTO(saved);
        }
        return null;
    }

    public boolean deleteSchedule(int id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ScheduleDTO toDTO(ScheduleEntity entity) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setMovieId(entity.getMovieId());
        dto.setRoomId(entity.getRoomId());
        dto.setCinemaId(entity.getCinemaId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    private ScheduleEntity toEntity(ScheduleDTO dto) {
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(dto.getId());
        entity.setMovieId(dto.getMovieId());
        entity.setRoomId(dto.getRoomId());
        entity.setCinemaId(dto.getCinemaId());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setPrice(dto.getPrice());
        return entity;
    }
} 