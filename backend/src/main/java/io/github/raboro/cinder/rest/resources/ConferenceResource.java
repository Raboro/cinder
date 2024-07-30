package io.github.raboro.cinder.rest.resources;

import io.github.raboro.cinder.entities.Country;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import io.github.raboro.cinder.rest.dto.ConferenceDTO;
import io.github.raboro.cinder.services.ConferenceService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("conference")
public class ConferenceResource {

    private final ConferenceService service;

    @Autowired
    public ConferenceResource(ConferenceService service) {
        this.service = service;
    }

    private record MatchingCriteriaDTO(
            Country country,
            List<CategoryDTO> categories,
            float maxCost,
            int startTime
    ) {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConferenceDTO> getConferencesMatchingCriteriaByPage(
            @QueryParam("page") int page,
            @QueryParam("size") int size,
            @RequestBody MatchingCriteriaDTO matchingCriteria
    ) {
        return service.getConferencesMatchingCriteriaByPage(
                page,
                size,
                matchingCriteria.country(),
                matchingCriteria.categories(),
                matchingCriteria.maxCost(),
                matchingCriteria.startTime()
        );
    }
}
