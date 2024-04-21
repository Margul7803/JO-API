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

import fr.joapi.jobackend.dto.ClientOrderDto;
import fr.joapi.jobackend.model.ClientOrder;
import fr.joapi.jobackend.service.ClientOrderService;

@RestController
@RequestMapping("/client-orders")
public class ClientOrderController {
    private final ClientOrderService service;

    @Autowired
    public ClientOrderController(ClientOrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientOrder>> findAll() {
        return new ResponseEntity<>(service.findAllClientOrders(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientOrder> findOneById(@PathVariable String uuid) {
        ClientOrder clientOrder = service.findClientOrderById(uuid);
        if (clientOrder != null) {
            return new ResponseEntity<>(service.findClientOrderById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ClientOrder> save(@Valid @RequestBody ClientOrderDto clientOrder) {
        ClientOrder createdclientOrder = service.create(clientOrder);
        return new ResponseEntity<>(createdclientOrder, HttpStatus.CREATED);
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
            @RequestBody ClientOrderDto clientOrder) {
        boolean isUpdated = service.update(uuid, clientOrder);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
