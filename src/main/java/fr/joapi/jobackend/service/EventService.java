package fr.joapi.jobackend.service;

import org.springframework.stereotype.Service;

import fr.joapi.jobackend.dto.EventDto;
import fr.joapi.jobackend.model.Event;
import fr.joapi.jobackend.repository.EventRepository;
import fr.joapi.jobackend.repository.StadiumRepository;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventService {

    private final EventRepository repository;

    private final StadiumRepository stadiumRepository;

    @Autowired
    public EventService(EventRepository repository, StadiumRepository stadiumRepository) {
        this.repository = repository;
        this.stadiumRepository = stadiumRepository;
    }

    public List<Event> findAllEvents() {
        return repository.findAll();
    }

    public Event findEventById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Event create(EventDto event) {
        if (event.getMaxEntry() > (stadiumRepository.findOneByUuid(event.getStadium().getUuid()).get().getCapacity())) {
            throw new RuntimeException("Error: Event entry is higher than stadium capacity");
        }
        Event eventToCreate = new Event(event.getName(), event.getMaxEntry(), event.getPrice(), event.getStartDate(),
                event.getEndDate(),
                event.getStatus(), event.getTicketing(), event.getStadium());
        return repository.save(eventToCreate);
    }

    @Transactional
    public boolean delete(String uuid) {
        Event eventToDelete = findEventById(uuid);
        if (eventToDelete != null) {
            repository.delete(eventToDelete);
            return true;
        }
        return false;
    }

    public boolean update(String uuid, EventDto event) {
        Event eventToUpdate = findEventById(uuid);

        if (eventToUpdate != null) {
            eventToUpdate.setName(event.getName());
            eventToUpdate.setStartDate(event.getStartDate());
            eventToUpdate.setEndDate(event.getStartDate());
            eventToUpdate.setMaxEntry(event.getMaxEntry());
            eventToUpdate.setStatus(event.getStatus());
            eventToUpdate.setTicketing(event.getTicketing());
            eventToUpdate.setStadium(event.getStadium());
            repository.save(eventToUpdate);
            return true;
        }
        return false;
    }

    public boolean updatePartielle(String uuid, EventDto event) {
        Event eventToUpdate = findEventById(uuid);

        if (eventToUpdate != null) {
            if (!event.getName().isEmpty()) {
                eventToUpdate.setName(event.getName());
            }
            if (event.getStartDate() != null) {
                eventToUpdate.setStartDate(event.getStartDate());
            }
            if (event.getEndDate() != null) {
                eventToUpdate.setEndDate(event.getEndDate());
            }
            if (event.getMaxEntry() != null) {
                eventToUpdate.setMaxEntry(event.getMaxEntry());
            }
            if (event.getPrice() != 0) {
                eventToUpdate.setPrice(event.getPrice());
            }
            if (event.getStatus() != null) {
                eventToUpdate.setStatus(event.getStatus());
            }
            if (event.getTicketing() != null) {
                eventToUpdate.setTicketing(event.getTicketing());
            }
            if (event.getStadium() != null) {
                eventToUpdate.setStadium(event.getStadium());
            }
            repository.save(eventToUpdate);
            return true;
        }
        return false;
    }
}
