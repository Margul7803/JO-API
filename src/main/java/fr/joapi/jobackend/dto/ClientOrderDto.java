package fr.joapi.jobackend.dto;

import java.util.List;

import fr.joapi.jobackend.model.User;
import fr.joapi.jobackend.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientOrderDto {
    private List<Ticket> tickets;

    private User user;
}
