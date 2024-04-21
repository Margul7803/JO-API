package fr.joapi.jobackend.model;

import lombok.*;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @OneToMany(mappedBy = "clientOrder")
    private List<Ticket> tickets;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_uuid")
    private Client client;
}
