package io.github.raboro.cinder.services;

import io.github.raboro.cinder.entities.Currency;
import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import io.github.raboro.cinder.rest.mapper.CurrencyMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyMapper mapper;

    public CurrencyService(CurrencyMapper mapper) {
        this.mapper = mapper;
    }

    public List<CurrencyDTO> getAllCurrencies() {
        return Arrays.stream(Currency.values())
                .map(mapper::toDTO)
                .toList();
    }
}
