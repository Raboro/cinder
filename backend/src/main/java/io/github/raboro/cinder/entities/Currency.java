package io.github.raboro.cinder.entities;

import jakarta.persistence.*;
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
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private char symbol;

    @OneToOne
    private Settings settings;

    public Currency(String name, char symbol, Settings settings) {
        this.name = name;
        this.symbol = symbol;
        this.settings = settings;
    }
}
