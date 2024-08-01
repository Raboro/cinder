package io.github.raboro.cinder.rest.dto;

import java.util.List;

public record MatchedConferenceDTO(List<CategoryDTO> categories,
                                   DurationDTO duration,
                                   LocationDTO location,
                                   String name,
                                   float cost,
                                   String website,
                                   boolean accepted,
                                   boolean past) {
}
