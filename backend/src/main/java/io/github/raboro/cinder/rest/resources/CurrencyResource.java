package io.github.raboro.cinder.rest.resources;


import io.github.raboro.cinder.rest.dto.CurrencyDTO;
import io.github.raboro.cinder.rest.mapper.CurrencyMapper;
import io.github.raboro.cinder.services.CurrencyService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Path("currency")
public class CurrencyResource {

    private final CurrencyService service;
    private final CurrencyMapper mapper;

    @Autowired
    public CurrencyResource(CurrencyService service, CurrencyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CurrencyDTO> getCurrencies() {
        return service.getAllCurrencies().stream().map(mapper::toDTO).toList();
    }
}
