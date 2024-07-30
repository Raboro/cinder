package io.github.raboro.cinder.rest.dto;

import java.net.URL;
import java.util.List;

public record ConferenceDTO(List<CategoryDTO> categories,
                            DurationDTO duration,
                            LocationDTO location,
                            String name,
                            float cost,
                            URL website) {
}
