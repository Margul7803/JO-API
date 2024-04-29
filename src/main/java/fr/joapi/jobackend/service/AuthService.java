package fr.joapi.jobackend.service;

import fr.joapi.jobackend.constant.Role;
import fr.joapi.jobackend.dto.LoginDto;
import fr.joapi.jobackend.dto.RegisterDto;
import fr.joapi.jobackend.repository.UserRepository;
import fr.joapi.jobackend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthService(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterDto input) {
		User user = new User();
		user.setLastName(input.getLastName());
		user.setName(input.getName());
		user.setEmail(input.getEmail());
		user.setAddress(input.getAddress());
		user.setPhone(input.getPhone());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return userRepository.save(user);
	}

	public User authenticate(LoginDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getEmail(),
						input.getPassword()));

		return userRepository.findByEmail(input.getEmail())
				.orElseThrow();
	}
}