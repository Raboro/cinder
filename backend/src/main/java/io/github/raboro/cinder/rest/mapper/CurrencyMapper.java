package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Currency;
import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class CurrencyMapper implements EntityMapper<Currency, CurrencyDTO>{

    @Override
    public CurrencyDTO toDTO(Currency currency) {
        return new CurrencyDTO(currency.getName(), currency.getSymbol());
    }

    @Override
    public Currency toEntity(CurrencyDTO dto) {
        return Arrays.stream(Currency.values())
                .filter(sameSymbol(dto))
                .findFirst()
                .orElse(Currency.EUR);
    }

    private Predicate<Currency> sameSymbol(CurrencyDTO dto) {
        return currency -> currency.getSymbol() == dto.symbol();
    }
}
