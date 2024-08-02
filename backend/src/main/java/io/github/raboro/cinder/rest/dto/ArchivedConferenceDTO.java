package io.github.raboro.cinder.rest.dto;

import java.util.List;

public record ArchivedConferenceDTO(List<CategoryDTO> categories,
                                    DurationDTO duration,
                                    LocationDTO location,
                                    String name,
                                    float cost,
                                    String website) {
}
