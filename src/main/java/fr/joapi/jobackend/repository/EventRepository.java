package fr.joapi.jobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.joapi.jobackend.model.Event;

public interface EventRepository extends JpaRepository<Event, String> {

    // SELECT * FROM Student where uuid = ?
    Optional<Event> findOneByUuid(String uuid);
}
