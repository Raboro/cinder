package io.github.raboro.cinder.rest.resources;

import io.github.raboro.cinder.entities.Country;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import io.github.raboro.cinder.rest.dto.ConferenceDTO;
import io.github.raboro.cinder.services.ConferenceService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ConferenceDTO addConference(@RequestBody ConferenceDTO dto) {
        return service.addConference(dto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ConferenceDTO putConference(@PathParam("id") UUID id, @RequestBody ConferenceDTO dto) {
        return service.putConference(id, dto);
    }
}
