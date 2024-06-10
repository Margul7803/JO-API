package fr.joapi.jobackend.model;

import lombok.*;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientLastName;

    @ManyToOne
    @JoinColumn(name = "clientOrder_uuid", nullable = false)
    @JsonBackReference
    private ClientOrder clientOrder;

    @ManyToOne
    @JoinColumn(name = "event_uuid", nullable = false)
    private Event event;

    public Ticket(String clientLastName, String clientName, Event event, ClientOrder clientOrder) {
        this.clientLastName = clientLastName;
        this.clientName = clientName;
        this.clientOrder = clientOrder;
        this.event = event;
    }
}
