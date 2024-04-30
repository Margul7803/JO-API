package fr.joapi.jobackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.joapi.jobackend.dto.TicketDto;
import fr.joapi.jobackend.model.Ticket;
import fr.joapi.jobackend.model.Event;
import fr.joapi.jobackend.repository.EventRepository;
import fr.joapi.jobackend.repository.TicketRepository;
import jakarta.transaction.Transactional;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final EventRepository eventRepository;

    @Autowired
    public TicketService(TicketRepository repository, EventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public List<Ticket> findAllTickets() {
        return repository.findAll();
    }

    public Ticket findTicketById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Ticket create(TicketDto ticket) {
        Event event = eventRepository.findOneByUuid(ticket.getEvent().getUuid()).get();
        Integer eventEntry = repository.findAllByEventUuid(ticket.getEvent().getUuid()).size();

        if (event.getMaxEntry() == eventEntry) {
            throw new RuntimeException("Error: No more entry allowed on this event");
        }
        if (event.getTicketing() == false) {
            throw new RuntimeException("Error: Ticketing is closed");
        }
        if (event.getEndDate().compareTo(new Date()) < 0) {
            throw new RuntimeException("Error: Event is over");
        }
        // if (event.getStartDate().compareTo(new Date()) == 0) {
        // throw new RuntimeException("Error: You already registered to antoher event
        // this day");
        // }

        Ticket ticketToCreate = new Ticket(ticket.getClientLastName(), ticket.getClientName(), ticket.getEvent(),
                ticket.getClientOrder());
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
            ticketToUpdate.setClientOrder(ticket.getClientOrder());
            repository.save(ticketToUpdate);
            return true;
        }
        return false;
    }
}
