package io.github.raboro.cinder.services;

import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    CurrencyService service;

    @Test
    void getAllCurrenciesShouldGetAllCurrenciesAsDTOs() {
        List<CurrencyDTO> currencies = service.getAllCurrencies();
        assertEquals(2, currencies.size());
        assertEquals('€', currencies.get(0).symbol());
        assertEquals("Euro", currencies.get(0).name());
    }
}
