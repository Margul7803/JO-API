package fr.joapi.jobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.joapi.jobackend.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    Optional<Ticket> findOneByUuid(String uuid);
}
