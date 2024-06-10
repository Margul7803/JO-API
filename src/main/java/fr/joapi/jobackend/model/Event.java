package fr.joapi.jobackend.model;

import lombok.*;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "date", "stadium_uuid" })
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer maxEntry;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Paris")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Paris")
    private Date endDate;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private Boolean ticketing;

    @ManyToOne
    @JoinColumn(name = "stadium_uuid")
    @JsonBackReference
    private Stadium stadium;

    public Event(String name, Integer maxEntry, float price, Date starDate, Date endDate, Boolean status,
            Boolean ticketing,
            Stadium stadium) {
        this.name = name;
        this.maxEntry = maxEntry;
        this.startDate = starDate;
        this.endDate = endDate;
        this.status = status;
        this.ticketing = ticketing;
        this.stadium = stadium;
        this.price = price;
    }
}
