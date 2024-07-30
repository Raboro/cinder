package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Settings;
import io.github.raboro.cinder.rest.dto.SettingsDTO;
import org.springframework.stereotype.Component;

@Component
public class SettingsMapper implements EntityMapper<Settings, SettingsDTO> {

    @Override
    public SettingsDTO toDTO(Settings settings) {
        return new SettingsDTO(
                settings.getMaxCost(),
                settings.getMinCost(),
                new CurrencyMapper().toDTO(settings.getCurrency())
        );
    }

    @Override
    public Settings toEntity(SettingsDTO dto) {
        return new Settings(
                dto.maxCost(),
                dto.minCost(),
                new CurrencyMapper().toEntity(dto.currency())
        );
    }
}
