package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Currency;
import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CurrencyMapperTest {

    @Autowired
    CurrencyMapper mapper;

    @Test
    void toEntityWithValidCurrencyDTOShouldProduceCorrectEntity() {
        Currency currency = mapper.toEntity(new CurrencyDTO("Euro", '€'));
        assertEquals('€', currency.getSymbol());
    }

    @Test
    void notPresentCurrencyShouldFallbackToEuro() {
        Currency currency = mapper.toEntity(new CurrencyDTO("Test", 'a'));
        assertEquals('€', currency.getSymbol());
    }

}