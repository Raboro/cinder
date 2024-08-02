package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.ArchivedConference;
import io.github.raboro.cinder.rest.dto.ArchivedConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArchivedConferenceMapper implements EntityMapper<ArchivedConference, ArchivedConferenceDTO> {

    private final CategoryMapper categoryMapper;
    private final DurationMapper durationMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public ArchivedConferenceMapper(CategoryMapper categoryMapper, DurationMapper durationMapper,
                            LocationMapper locationMapper) {
        this.categoryMapper = categoryMapper;
        this.durationMapper = durationMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public ArchivedConferenceDTO toDTO(ArchivedConference conference) {
        return new ArchivedConferenceDTO(
                conference.getCategories().stream().map(categoryMapper::toDTO).toList(),
                durationMapper.toDTO(conference.getDuration()),
                locationMapper.toDTO(conference.getLocation()),
                conference.getName(),
                conference.getCost(),
                conference.getWebsite()
        );
    }

    @Override
    public ArchivedConference toEntity(ArchivedConferenceDTO dto) {
        return new ArchivedConference(
                dto.categories().stream().map(categoryMapper::toEntity).toList(),
                durationMapper.toEntity(dto.duration()),
                locationMapper.toEntity(dto.location()),
                dto.name(),
                dto.cost(),
                dto.website()
        );
    }
}
