package io.github.raboro.cinder.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int maxCost;
    private int minCost;

    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    public Settings(int maxCost, int minCost, Currency currency) {
        this.maxCost = maxCost;
        this.minCost = minCost;
        this.currency = currency;
    }
}
