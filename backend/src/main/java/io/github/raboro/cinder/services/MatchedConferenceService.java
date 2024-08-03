package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.CategoryRepository;
import io.github.raboro.cinder.dao.DurationRepository;
import io.github.raboro.cinder.dao.LocationRepository;
import io.github.raboro.cinder.dao.MatchedConferenceRepository;
import io.github.raboro.cinder.entities.*;
import io.github.raboro.cinder.rest.dto.MatchedConferenceDTO;
import io.github.raboro.cinder.rest.mapper.CategoryMapper;
import io.github.raboro.cinder.rest.mapper.DayMapper;
import io.github.raboro.cinder.rest.mapper.MatchedConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchedConferenceService {

    private final MatchedConferenceRepository repository;
    private final MatchedConferenceMapper mapper;
    private final CategoryMapper categoryMapper;
    private final DayMapper dayMapper;
    private final DurationRepository durationRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MatchedConferenceService(MatchedConferenceRepository repository,
                                    MatchedConferenceMapper mapper,
                                    CategoryMapper categoryMapper,
                                    DayMapper dayMapper,
                                    DurationRepository durationRepository,
                                    LocationRepository locationRepository,
                                    CategoryRepository categoryRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
        this.dayMapper = dayMapper;
        this.durationRepository = durationRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public MatchedConferenceDTO addMatchedConference(MatchedConferenceDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void deleteMatchedConference(UUID id) {
        repository.deleteById(id);
    }

    public MatchedConferenceDTO putMatchedConference(UUID id, MatchedConferenceDTO dto) {
        Optional<MatchedConference> byId = repository.findById(id);
        if (byId.isEmpty()) {
            return mapper.toDTO(repository.save(mapper.toEntity(dto)));
        }

        MatchedConference conference = byId.get();
        Location location = conference.getLocation();
        location.setCountry(dto.location().country());
        location.setStreet(dto.location().street());
        location.setHouseNumber(dto.location().houseNumber());
        locationRepository.save(location);
        conference.setLocation(location);

        Duration duration = conference.getDuration();
        duration.setStartDay(dayMapper.toEntity(dto.duration().startDay()));
        duration.setEndDay(dayMapper.toEntity(dto.duration().endDay()));
        durationRepository.save(duration);
        conference.setDuration(duration);

        List<Category> categories = dto.categories().stream().map(categoryMapper::toEntity).toList();
        categoryRepository.saveAll(categories);
        conference.setCategories(categories);

        conference.setWebsite(dto.website());
        conference.setName(dto.name());
        conference.setCost(dto.cost());
        conference.setAccepted(dto.accepted());
        conference.setPast(dto.past());
        return mapper.toDTO(repository.save(conference));
    }
}
