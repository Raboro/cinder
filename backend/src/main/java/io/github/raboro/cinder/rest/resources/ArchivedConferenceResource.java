package io.github.raboro.cinder.rest.resources;

import io.github.raboro.cinder.rest.dto.ArchivedConferenceDTO;
import io.github.raboro.cinder.services.ArchivedConferenceService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Path("conference/match/archived")
public class ArchivedConferenceResource {

    private final ArchivedConferenceService service;

    @Autowired
    public ArchivedConferenceResource(ArchivedConferenceService service) {
        this.service = service;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArchivedConferenceDTO> getAllByPage(
            @QueryParam("page") int page,
            @QueryParam("size") int size
    ) {
        return service.getAllByPage(page, size);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArchivedConferenceDTO addArchivedConference(@RequestBody ArchivedConferenceDTO dto) {
        return service.addArchivedConference(dto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArchivedConferenceDTO putArchivedConference(@PathParam("id") UUID id,
                                                       @RequestBody ArchivedConferenceDTO dto) {
        return service.putArchivedConference(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteArchivedConference(@PathParam("id") UUID id) {
        service.deleteArchivedConference(id);
    }
}
