package io.github.raboro.cinder.services;

import io.github.raboro.cinder.entities.Settings;
import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import io.github.raboro.cinder.rest.dto.SettingsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SettingsServiceTest {

    @Autowired
    SettingsService service;

    @Test
    void getSettingsWithNoInDBShouldBeEmptyOptional() {
        Optional<Settings> settings = service.getSettings();
        assertTrue(settings.isEmpty());
    }

    @Test
    void patchSettingsShouldUpdateOrDirectlyBeSettings() {
        CurrencyDTO currency = new CurrencyDTO("Euro", '€');
        int maxCost = 20;
        int minCost = 10;

        for (int i = 0; i < 2; i++) {
            SettingsDTO settingsDTO = service.patchSettings(
                    new SettingsDTO(
                            maxCost,
                            minCost,
                            currency
                    )
            );

            assertEquals(minCost, settingsDTO.minCost());
            assertEquals(maxCost, settingsDTO.maxCost());
            assertEquals(currency, settingsDTO.currency());
        }
    }
}