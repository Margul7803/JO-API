package fr.joapi.jobackend.controller;

import fr.joapi.jobackend.dto.LoginDto;
import fr.joapi.jobackend.dto.LoginResponseDto;
import fr.joapi.jobackend.dto.RegisterDto;
import fr.joapi.jobackend.dto.RegisterResponseDto;
import fr.joapi.jobackend.model.User;
import fr.joapi.jobackend.service.AuthService;
import fr.joapi.jobackend.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    @Autowired
    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        RegisterResponseDto registerResponse = new RegisterResponseDto(
                registeredUser.getEmail(),
                registeredUser.getLastName(),
                registeredUser.getName(),
                registeredUser.getPhone(),
                registeredUser.getAddress());

        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(
            @RequestBody LoginDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto(jwtToken, jwtService.getExpirationTime());

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
