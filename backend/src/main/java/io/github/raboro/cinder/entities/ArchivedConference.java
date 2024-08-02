package io.github.raboro.cinder.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class ArchivedConference extends Conference {

    public ArchivedConference(List<Category> categories, Duration duration, Location location, String name, float cost,
                      String website) {
        super(categories, duration, location, name, cost, website);
    }
}
