package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Location;
import io.github.raboro.cinder.rest.dto.LocationDTO;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements EntityMapper<Location, LocationDTO> {

    @Override
    public LocationDTO toDTO(Location location) {
        return new LocationDTO(location.getCountry(), location.getStreet(), location.getHouseNumber());
    }

    @Override
    public Location toEntity(LocationDTO dto) {
        return new Location(dto.country(), dto.street(), dto.houseNumber());
    }
}
