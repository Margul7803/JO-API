package fr.joapi.jobackend.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
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
import fr.joapi.jobackend.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/client-orders")
public class ClientOrderController {
    private final ClientOrderService service;
    private final JwtService jwtService;

    @Autowired
    public ClientOrderController(ClientOrderService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<ClientOrder>> findAll() {
        return new ResponseEntity<>(service.findAllClientOrders(), HttpStatus.OK);
    }

    @GetMapping("/me")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> findAllClientOrdersByUser(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            final String userEmail = jwtService.extractUsername(bearerToken);
            return new ResponseEntity<>(userEmail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<ClientOrder> findOneById(@PathVariable String uuid) {
        ClientOrder clientOrder = service.findClientOrderById(uuid);
        if (clientOrder != null) {
            return new ResponseEntity<>(service.findClientOrderById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<ClientOrder> save(@Valid @RequestBody ClientOrderDto clientOrder) {
        ClientOrder createdclientOrder = service.create(clientOrder);
        return new ResponseEntity<>(createdclientOrder, HttpStatus.CREATED);
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
            @RequestBody ClientOrderDto clientOrder) {
        boolean isUpdated = service.update(uuid, clientOrder);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
