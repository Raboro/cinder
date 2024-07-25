package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.SettingsRepository;
import io.github.raboro.cinder.entities.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Optional<Settings> getSettings() {
        List<Settings> settingsList = settingsRepository.findAll();
        return settingsList.isEmpty() ? Optional.empty() : Optional.of(settingsList.get(0));
    }
}
