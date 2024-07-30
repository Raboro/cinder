package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Day;
import io.github.raboro.cinder.rest.dto.DayDTO;
import org.springframework.stereotype.Component;

@Component
public class DayMapper implements EntityMapper<Day, DayDTO> {

    @Override
    public DayDTO toDTO(Day day) {
        return new DayDTO(day.getDate(), day.getStartTime(), day.getEndTime());
    }

    @Override
    public Day toEntity(DayDTO dto) {
        return new Day(dto.date(), dto.startTime(), dto.endTime());
    }
}
