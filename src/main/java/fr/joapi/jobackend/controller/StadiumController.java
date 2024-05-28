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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import fr.joapi.jobackend.dto.StadiumDto;
import fr.joapi.jobackend.model.Stadium;
import fr.joapi.jobackend.service.StadiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/stadiums")
@EnableMethodSecurity
public class StadiumController {
    private final StadiumService service;

    @Autowired
    public StadiumController(StadiumService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<Stadium>> findAll() {
        return new ResponseEntity<>(service.findAllStadiums(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Stadium> findOneById(@PathVariable String uuid) {
        Stadium stadium = service.findStadiumById(uuid);
        if (stadium != null) {
            return new ResponseEntity<>(service.findStadiumById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Stadium> save(@Valid @RequestBody StadiumDto stadium) {
        Stadium createdStadium = service.create(stadium);
        return new ResponseEntity<>(createdStadium, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> mettreAJourTotalement(
            @PathVariable String uuid,
            @RequestBody StadiumDto stadium) {
        boolean isUpdated = service.update(uuid, stadium);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
