package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Currency;
import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public CurrencyDTO toDTO(Currency currency) {
        return new CurrencyDTO(
                currency.getName(),
                currency.getSymbol(),
                new SettingsMapper().toDTO(currency.getSettings())
        );
    }

    public Currency toEntity(CurrencyDTO dto) {
        return new Currency(
                dto.name(),
                dto.symbol(),
                new SettingsMapper().toEntity(dto.settings())
        );
    }
}
