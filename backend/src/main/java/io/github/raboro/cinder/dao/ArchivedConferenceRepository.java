package io.github.raboro.cinder.dao;

import io.github.raboro.cinder.entities.ArchivedConference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArchivedConferenceRepository extends JpaRepository<ArchivedConference, UUID> {
}
