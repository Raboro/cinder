package io.github.raboro.cinder.rest.resources;

import io.github.raboro.cinder.rest.dto.MatchedConferenceDTO;
import io.github.raboro.cinder.services.MatchedConferenceService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Path("conference/match")
public class MatchedConferenceResource {

    private final MatchedConferenceService service;

    public MatchedConferenceResource(MatchedConferenceService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MatchedConferenceDTO addMatchedConference(@RequestBody MatchedConferenceDTO dto) {
        return service.addMatchedConference(dto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MatchedConferenceDTO putMatchedConference(@PathParam("id") UUID id, @RequestBody MatchedConferenceDTO dto) {
        return service.putMatchedConference(id, dto);
    }


    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMatchedConference(@PathParam("id") UUID id) {
        service.deleteMatchedConference(id);
    }
}
