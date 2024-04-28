package fr.joapi.jobackend.dto;

import fr.joapi.jobackend.model.ClientOrder;
import fr.joapi.jobackend.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketDto {
    private String clientName;

    private String clientLastName;

    private Event event;

    private ClientOrder clientOrder;
}
