package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Duration;
import io.github.raboro.cinder.rest.dto.DurationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DurationMapper implements EntityMapper<Duration, DurationDTO> {

    private final DayMapper dayMapper;

    @Autowired
    public DurationMapper(DayMapper dayMapper) {
        this.dayMapper = dayMapper;
    }

    @Override
    public DurationDTO toDTO(Duration duration) {
        return new DurationDTO(
                dayMapper.toDTO(duration.getStartDay()),
                dayMapper.toDTO(duration.getEndDay())
        );
    }

    @Override
    public Duration toEntity(DurationDTO dto) {
        return new Duration(
                dayMapper.toEntity(dto.startDay()),
                dayMapper.toEntity(dto.endDay())
        );
    }
}
