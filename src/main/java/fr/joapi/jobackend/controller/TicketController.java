package fr.joapi.jobackend.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.joapi.jobackend.dto.TicketDto;
import fr.joapi.jobackend.model.Ticket;
import fr.joapi.jobackend.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        return new ResponseEntity<>(service.findAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Ticket> findOneById(@PathVariable String uuid) {
        Ticket ticket = service.findTicketById(uuid);
        if (ticket != null) {
            return new ResponseEntity<>(service.findTicketById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Ticket> save(@Valid @RequestBody TicketDto ticket) {
        Ticket createdTicket = service.create(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourTotalement(
            @PathVariable String uuid,
            @RequestBody TicketDto ticket) {
        boolean isUpdated = service.update(uuid, ticket);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
