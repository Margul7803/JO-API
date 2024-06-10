package fr.joapi.jobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.joapi.jobackend.dto.ClientOrderDto;
import fr.joapi.jobackend.model.ClientOrder;
import fr.joapi.jobackend.model.Ticket;
import fr.joapi.jobackend.repository.ClientOrderRepository;
import jakarta.transaction.Transactional;

@Service
public class ClientOrderService {
    private final ClientOrderRepository repository;

    @Autowired
    public ClientOrderService(ClientOrderRepository repository) {
        this.repository = repository;
    }

    public List<ClientOrder> findAllClientOrders() {
        return repository.findAll();
    }

    public List<ClientOrder> findAllClientOrdersByUser(String userEmail) {
        return repository.findAllByUserEmail(userEmail);
    }

    public ClientOrder findClientOrderById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public ClientOrder create(ClientOrderDto clientOrder) {
        float finalPrice = 0;
        for (Ticket ticket : clientOrder.getTickets()) {
            finalPrice = finalPrice + ticket.getEvent().getPrice();
        }

        if (clientOrder.getTickets().size() > 3)
            finalPrice = finalPrice * 0.9f;
        else if (clientOrder.getTickets().size() > 6)
            finalPrice = finalPrice * 0.8f;
        else if (clientOrder.getTickets().size() > 9)
            finalPrice = finalPrice * 0.7f;

        ClientOrder clientOrderToCreate = new ClientOrder(clientOrder.getTickets(), clientOrder.getUser(), finalPrice);
        return repository.save(clientOrderToCreate);
    }

    @Transactional
    public boolean delete(String uuid) {
        ClientOrder clientOrderToDelete = findClientOrderById(uuid);
        if (clientOrderToDelete != null) {
            repository.delete(clientOrderToDelete);
            return true;
        }
        return false;
    }

    public boolean update(String uuid, ClientOrderDto clientOrder) {
        ClientOrder clientOrderToUpdate = findClientOrderById(uuid);

        if (clientOrderToUpdate != null) {
            clientOrderToUpdate.setUser(clientOrder.getUser());
            clientOrderToUpdate.setTickets(clientOrder.getTickets());
            repository.save(clientOrderToUpdate);
            return true;
        }
        return false;
    }
}
