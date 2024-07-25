package io.github.raboro.cinder.rest.dto;

import java.util.UUID;

public record SettingsDTO(int maxCost, int minCost, CurrencyDTO currency) {
}
