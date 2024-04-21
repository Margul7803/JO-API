package fr.joapi.jobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.joapi.jobackend.dto.TicketDto;
import fr.joapi.jobackend.model.Ticket;
import fr.joapi.jobackend.repository.TicketRepository;
import jakarta.transaction.Transactional;

@Service
public class TicketService {
    private final TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAllTickets() {
        return repository.findAll();
    }

    public Ticket findTicketById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Ticket create(TicketDto ticket) {
        Ticket ticketToCreate = new Ticket(ticket.getClientLastName(), ticket.getClientName(), ticket.getEvent());
        return repository.save(ticketToCreate);
    }

    @Transactional
    public boolean delete(String uuid) {
        Ticket ticketToDelete = findTicketById(uuid);
        if (ticketToDelete != null) {
            repository.delete(ticketToDelete);
            return true;
        }
        return false;
    }

    public boolean update(String uuid, TicketDto ticket) {
        Ticket ticketToUpdate = findTicketById(uuid);

        if (ticketToUpdate != null) {
            ticketToUpdate.setClientLastName(ticket.getClientLastName());
            ticketToUpdate.setClientName(ticket.getClientName());
            ticketToUpdate.setEvent(ticket.getEvent());
            repository.save(ticketToUpdate);
            return true;
        }
        return false;
    }
}
