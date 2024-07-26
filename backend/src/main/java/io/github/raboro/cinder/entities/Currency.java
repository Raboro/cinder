package io.github.raboro.cinder.entities;

public enum Currency {

    EUR("Euro", '€'),
    USD("USD", '$'),
    ;

    private final String name;
    private final char symbol;

    Currency(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}
