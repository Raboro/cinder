package io.github.raboro.cinder.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class MatchedConference extends Conference {
    private boolean accepted;
    private boolean past;

    public MatchedConference(List<Category> categories,
                             Duration duration,
                             Location location,
                             String name,
                             float cost,
                             String website,
                             boolean accepted,
                             boolean past) {
        setCategories(categories);
        setDuration(duration);
        setLocation(location);
        setName(name);
        setCost(cost);
        setWebsite(website);
        this.accepted = accepted;
        this.past = past;
    }
}
