package fr.joapi.jobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.joapi.jobackend.model.Event;

public interface EventRepository extends JpaRepository<Event, String> {
    Optional<Event> findOneByUuid(String uuid);
}
