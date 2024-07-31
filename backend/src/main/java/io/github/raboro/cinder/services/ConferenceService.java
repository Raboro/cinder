package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.CategoryRepository;
import io.github.raboro.cinder.dao.ConferenceRepository;
import io.github.raboro.cinder.dao.DurationRepository;
import io.github.raboro.cinder.dao.LocationRepository;
import io.github.raboro.cinder.entities.Conference;
import io.github.raboro.cinder.entities.Country;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import io.github.raboro.cinder.rest.dto.ConferenceDTO;
import io.github.raboro.cinder.rest.mapper.CategoryMapper;
import io.github.raboro.cinder.rest.mapper.ConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private static final int SIZE_DEFAULT = 3;
    private final ConferenceRepository repository;
    private final ConferenceMapper mapper;
    private final CategoryMapper categoryMapper;
    private final DurationRepository durationRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ConferenceService(
            ConferenceRepository repository,
            ConferenceMapper mapper,
            CategoryMapper categoryMapper,
            DurationRepository durationRepository,
            LocationRepository locationRepository,
            CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
        this.mapper = mapper;
        this.durationRepository = durationRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ConferenceDTO> getConferencesMatchingCriteriaByPage(int page,
                                                                    int size,
                                                                    Country country,
                                                                    List<CategoryDTO> categories,
                                                                    float maxCost,
                                                                    int startTime) {
        if (size == 0) {
            size = SIZE_DEFAULT;
        }
        Page<Conference> conferencesAsPage = repository.findByCountryAndCategoriesAndMaxCostAndFuture(
                country,
                categories.stream().map(categoryMapper::toEntity).toList(),
                maxCost,
                startTime,
                PageRequest.of(page, size)
        );
        return conferencesAsPage.get().map(mapper::toDTO).toList();
    }

    public ConferenceDTO addConference(ConferenceDTO dto) {
        Conference entity = mapper.toEntity(dto);
        categoryRepository.saveAll(entity.getCategories());
        durationRepository.save(entity.getDuration());
        locationRepository.save(entity.getLocation());
        return mapper.toDTO(repository.save(entity));
    }
}
