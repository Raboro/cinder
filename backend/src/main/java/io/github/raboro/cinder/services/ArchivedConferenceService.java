package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.ArchivedConferenceRepository;
import io.github.raboro.cinder.dao.CategoryRepository;
import io.github.raboro.cinder.dao.DurationRepository;
import io.github.raboro.cinder.dao.LocationRepository;
import io.github.raboro.cinder.entities.ArchivedConference;
import io.github.raboro.cinder.entities.Category;
import io.github.raboro.cinder.entities.Duration;
import io.github.raboro.cinder.entities.Location;
import io.github.raboro.cinder.rest.dto.ArchivedConferenceDTO;
import io.github.raboro.cinder.rest.mapper.ArchivedConferenceMapper;
import io.github.raboro.cinder.rest.mapper.CategoryMapper;
import io.github.raboro.cinder.rest.mapper.DayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArchivedConferenceService {

    private static final int SIZE_DEFAULT = 3;
    private final ArchivedConferenceRepository repository;
    private final ArchivedConferenceMapper mapper;
    private final CategoryMapper categoryMapper;
    private final DurationRepository durationRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final DayMapper dayMapper;

    @Autowired
    public ArchivedConferenceService(ArchivedConferenceRepository repository,
                                     ArchivedConferenceMapper mapper,
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

    public List<ArchivedConferenceDTO> getAllByPage(int page, int size) {
        if (size == 0) {
            size = SIZE_DEFAULT;
        }
        return repository.findAllWithPage(PageRequest.of(page, size))
                .get()
                .map(mapper::toDTO)
                .toList();
    }

    public ArchivedConferenceDTO addArchivedConference(ArchivedConferenceDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public ArchivedConferenceDTO putArchivedConference(UUID id, ArchivedConferenceDTO dto) {
        Optional<ArchivedConference> byId = repository.findById(id);
        if (byId.isEmpty()) {
            return mapper.toDTO(repository.save(mapper.toEntity(dto)));
        }
        ArchivedConference conference = byId.get();

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
        return mapper.toDTO(repository.save(conference));
    }

    public void deleteArchivedConference(UUID id) {
        repository.deleteById(id);
    }
}
