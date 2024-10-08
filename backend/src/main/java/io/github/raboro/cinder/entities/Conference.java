package io.github.raboro.cinder.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private Duration duration;

    @ManyToOne
    private Location location;

    private String name;
    private float cost;
    private String website;

    public Conference(List<Category> categories, Duration duration, Location location, String name, float cost,
                      String website) {
        this.categories = categories;
        this.duration = duration;
        this.location = location;
        this.name = name;
        this.cost = cost;
        this.website = website;
    }
}
