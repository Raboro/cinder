package io.github.raboro.cinder.dao;

import io.github.raboro.cinder.entities.Category;
import io.github.raboro.cinder.entities.Conference;
import io.github.raboro.cinder.entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, UUID> {

    @Query(
            """
                SELECT c from Conference c
                JOIN c.categories category
                WHERE c.location.country = :country
                    AND c.cost <= :maxCost
                    AND c.duration.startDay.startTime >= :startTime
                    AND category IN :categories
            """
    )
    Page<Conference> findByCountryAndCategoriesAndMaxCostAndFuture(
            @Param("country") Country country,
            @Param("categories") List<Category> categories,
            @Param("maxCost") float maxCost,
            @Param("startTime") int startTime,
            Pageable pageable
    );
}
