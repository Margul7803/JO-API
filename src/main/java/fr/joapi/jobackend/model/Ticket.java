package fr.joapi.jobackend.model;

import lombok.*;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
    @JoinColumn(name = "event_uuid")
    private Event event;

    public Ticket(String clientLastName, String clientName, Event event) {
        this.clientLastName = clientLastName;
        this.clientName = clientName;
        this.event = event;
    }
}
