package io.kevindewit.service.authentication.repository;

import io.kevindewit.service.authentication.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    Participant findByUsername(String username);
}
