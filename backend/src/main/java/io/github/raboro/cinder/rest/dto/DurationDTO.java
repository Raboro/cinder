package io.github.raboro.cinder.rest.dto;

import java.util.List;

public record DurationDTO(List<DayDTO> days, DayDTO startDay, DayDTO endDay) {
}
