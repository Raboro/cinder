package io.github.raboro.cinder.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Duration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "start_date")),
            @AttributeOverride(name = "startTime", column = @Column(name = "start_startTime")),
            @AttributeOverride(name = "endTime", column = @Column(name = "start_endTime"))
    })
    private Day startDay;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "end_date")),
            @AttributeOverride(name = "startTime", column = @Column(name = "end_startTime")),
            @AttributeOverride(name = "endTime", column = @Column(name = "end_endTime"))
    })
    private Day endDay;

    public Duration(Day startDay, Day endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }
}
