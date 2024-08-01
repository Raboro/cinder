package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.MatchedConference;
import io.github.raboro.cinder.rest.dto.MatchedConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchedConferenceMapper implements EntityMapper<MatchedConference, MatchedConferenceDTO> {

    private final CategoryMapper categoryMapper;
    private final DurationMapper durationMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public MatchedConferenceMapper(CategoryMapper categoryMapper, DurationMapper durationMapper,
                            LocationMapper locationMapper) {
        this.categoryMapper = categoryMapper;
        this.durationMapper = durationMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public MatchedConferenceDTO toDTO(MatchedConference matchedConference) {
        return new MatchedConferenceDTO(
                matchedConference.getCategories().stream().map(categoryMapper::toDTO).toList(),
                durationMapper.toDTO(matchedConference.getDuration()),
                locationMapper.toDTO(matchedConference.getLocation()),
                matchedConference.getName(),
                matchedConference.getCost(),
                matchedConference.getWebsite(),
                matchedConference.isAccepted(),
                matchedConference.isPast()
        );
    }

    @Override
    public MatchedConference toEntity(MatchedConferenceDTO dto) {
        return new MatchedConference(
            dto.categories().stream().map(categoryMapper::toEntity).toList(),
            durationMapper.toEntity(dto.duration()),
            locationMapper.toEntity(dto.location()),
            dto.name(),
            dto.cost(),
            dto.website(),
            dto.accepted(),
            dto.past()
        );
    }
}
