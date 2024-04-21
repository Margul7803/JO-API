package fr.joapi.jobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.joapi.jobackend.model.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, String> {
    Optional<Stadium> findOneByUuid(String uuid);
}
