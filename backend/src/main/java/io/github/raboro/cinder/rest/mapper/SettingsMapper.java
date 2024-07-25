package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Settings;
import io.github.raboro.cinder.rest.dto.SettingsDTO;
import org.springframework.stereotype.Component;

@Component
public class SettingsMapper {

    public SettingsDTO toDTO(Settings settings) {
        return new SettingsDTO(
                settings.getMinCost(),
                settings.getMaxCost(),
                new CurrencyMapper().toDTO(settings.getCurrency())
        );
    }

    public Settings toEntity(SettingsDTO dto) {
        return new Settings(
                dto.minCost(),
                dto.maxCost(),
                new CurrencyMapper().toEntity(dto.currency())
        );
    }
}
