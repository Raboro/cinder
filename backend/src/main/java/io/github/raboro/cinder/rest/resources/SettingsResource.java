package io.github.raboro.cinder.rest.resources;

import io.github.raboro.cinder.rest.dto.SettingsDTO;
import io.github.raboro.cinder.rest.mapper.SettingsMapper;
import io.github.raboro.cinder.services.SettingsService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Path("settings")
public class SettingsResource {

    private final SettingsService service;
    private final SettingsMapper mapper;

    @Autowired
    public SettingsResource(SettingsService service, SettingsMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<SettingsDTO> getSettings() {
        return service.getSettings().map(mapper::toDTO);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SettingsDTO patchSettings(@RequestBody SettingsDTO dto) {
        return service.patchSettings(dto);
    }
}
