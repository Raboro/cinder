package io.github.raboro.cinder.rest.dto;

public record SettingsDTO(int maxCost, int minCost, CurrencyDTO currency) {
}
