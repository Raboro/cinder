package io.github.raboro.cinder.rest.dto;

import io.github.raboro.cinder.entities.Country;

public record LocationDTO(Country country, String street, int houseNumber) {
}
