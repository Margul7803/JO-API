package fr.joapi.jobackend.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.joapi.jobackend.dto.EventDto;
import fr.joapi.jobackend.model.Event;
import fr.joapi.jobackend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return new ResponseEntity<>(service.findAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Event> findOneById(@PathVariable String uuid) {
        Event event = service.findEventById(uuid);
        if (event != null) {
            return new ResponseEntity<>(service.findEventById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> save(@Valid @RequestBody EventDto event) {
        try {
            Event createdEvent = service.create(event);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> mettreAJourTotalement(
            @PathVariable String uuid,
            @RequestBody EventDto event) {
        boolean isUpdated = service.update(uuid, event);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> mettreAjourPartiellement(
            @PathVariable String uuid,
            @RequestBody EventDto event) {
        boolean isUpdated = service.updatePartielle(uuid, event);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
