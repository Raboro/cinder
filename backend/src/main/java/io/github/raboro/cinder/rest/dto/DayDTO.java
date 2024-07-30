package io.github.raboro.cinder.rest.dto;

import java.sql.Date;

public record DayDTO(Date date, int startTime, int endTime) {
}
