package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.SettingsRepository;
import io.github.raboro.cinder.entities.Settings;
import io.github.raboro.cinder.rest.dto.SettingsDTO;
import io.github.raboro.cinder.rest.mapper.CurrencyMapper;
import io.github.raboro.cinder.rest.mapper.SettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;
    private final SettingsMapper mapper;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public SettingsService(SettingsRepository settingsRepository,
                           SettingsMapper mapper,
                           CurrencyMapper currencyMapper) {
        this.settingsRepository = settingsRepository;
        this.mapper = mapper;
        this.currencyMapper = currencyMapper;
    }

    public Optional<Settings> getSettings() {
        List<Settings> settingsList = settingsRepository.findAll();
        return settingsList.isEmpty() ? Optional.empty() : Optional.of(settingsList.get(0));
    }

    public SettingsDTO patchSettings(SettingsDTO dto) {
        return mapper.toDTO(settingsRepository.save(
                getSettings()
                        .map(updateSettings(dto))
                        .orElseGet(() -> mapper.toEntity(dto)))
        );
    }

    private Function<Settings, Settings> updateSettings(SettingsDTO dto) {
        return existingSettings -> {
            existingSettings.setCurrency(currencyMapper.toEntity(dto.currency()));
            existingSettings.setMinCost(dto.minCost());
            existingSettings.setMaxCost(dto.maxCost());
            return existingSettings;
        };
    }
}
