package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Conference;
import io.github.raboro.cinder.rest.dto.ConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConferenceMapper implements EntityMapper<Conference, ConferenceDTO> {

    private final CategoryMapper categoryMapper;
    private final DurationMapper durationMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public ConferenceMapper(CategoryMapper categoryMapper, DurationMapper durationMapper,
                            LocationMapper locationMapper) {
        this.categoryMapper = categoryMapper;
        this.durationMapper = durationMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public ConferenceDTO toDTO(Conference conference) {
        return new ConferenceDTO(
                conference.getCategories().stream().map(categoryMapper::toDTO).toList(),
                durationMapper.toDTO(conference.getDuration()),
                locationMapper.toDTO(conference.getLocation()),
                conference.getName(),
                conference.getCost(),
                conference.getWebsite()
        );
    }

    @Override
    public Conference toEntity(ConferenceDTO dto) {
        return new Conference(
                dto.categories().stream().map(categoryMapper::toEntity).toList(),
                durationMapper.toEntity(dto.duration()),
                locationMapper.toEntity(dto.location()),
                dto.name(),
                dto.cost(),
                dto.website()
        );
    }
}
