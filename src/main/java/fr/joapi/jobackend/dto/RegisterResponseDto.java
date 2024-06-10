package fr.joapi.jobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponseDto {
    private String email;

    private String lastName;

    private String name;

    private String phone;

    private String address;
}
