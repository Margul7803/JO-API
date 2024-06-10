package fr.joapi.jobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StadiumDto {
    private String name;

    private String address;

    private Integer capacity;
}
