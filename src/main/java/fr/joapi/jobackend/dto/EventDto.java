package fr.joapi.jobackend.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.joapi.jobackend.model.Stadium;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private String name;

    private Integer maxEntry;

    private float price;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Paris")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Paris")
    private Date endDate;

    private Boolean status;

    private Boolean ticketing;

    private Stadium stadium;
}
