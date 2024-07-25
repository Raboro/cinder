package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.CurrencyRepository;
import io.github.raboro.cinder.entities.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
