package fr.joapi.jobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.joapi.jobackend.model.ClientOrder;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, String> {
    Optional<ClientOrder> findOneByUuid(String uuid);

    List<ClientOrder> findAllByUserEmail(String userEmail);
}
