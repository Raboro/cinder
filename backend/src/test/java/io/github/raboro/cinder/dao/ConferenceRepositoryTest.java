package io.github.raboro.cinder.dao;

import io.github.raboro.cinder.entities.Category;
import io.github.raboro.cinder.entities.Conference;
import io.github.raboro.cinder.entities.Country;
import io.github.raboro.cinder.entities.Day;
import io.github.raboro.cinder.entities.Duration;
import io.github.raboro.cinder.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DurationRepository durationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        categoryRepository.deleteAll();
        durationRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    void emptyConferencesShouldReturnEmptyPage() {
        Category ai = new Category("AI");
        categoryRepository.save(ai);
        Page<Conference> conferences = repository.findByCountryAndCategoriesAndMaxCostAndFuture(
                Country.GER,
                List.of(ai),
                1500F,
                (int) Calendar.getInstance().getTimeInMillis(),
                PageRequest.of(0, 3)
        );

        assertEquals(3, conferences.getSize());
        assertEquals(0, conferences.get().toList().size());
    }

    @Test
    void savedConferenceWithMatchingConditionsShouldBeManpage() {
        Category ai = new Category("AI");
        categoryRepository.save(ai);

        Day day = new Day(
                "17.12.2024",
                (int) Calendar.getInstance().getTimeInMillis(),
                (int) Calendar.getInstance().getTimeInMillis() + 100
        );
        Duration duration = new Duration(List.of(day), day, day);
        durationRepository.save(duration);
        Location location = new Location(Country.GER, "FFF", 10);
        locationRepository.save(location);
        Conference conference1 = new Conference(
                List.of(ai),
                duration,
                location,
                "DevConf",
                1200F,
                "https://google.com"
        );
        repository.save(conference1);

        Page<Conference> conferences = repository.findByCountryAndCategoriesAndMaxCostAndFuture(
                Country.GER,
                List.of(ai),
                1500F,
                (int) Calendar.getInstance().getTimeInMillis() -10000,
                PageRequest.of(0, 3)
        );
        List<Conference> conferencesAsList = conferences.get().toList();
        assertEquals(1, conferencesAsList.size());
    }

}