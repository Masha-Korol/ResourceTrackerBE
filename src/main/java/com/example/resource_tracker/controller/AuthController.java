package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.dto.user.UserDto;
import com.example.resource_tracker.payloads.requests.LoginRequest;
import com.example.resource_tracker.payloads.requests.SignupRequest;
import com.example.resource_tracker.payloads.responses.JwtResponse;
import com.example.resource_tracker.payloads.responses.MessageResponse;
import com.example.resource_tracker.repository.UserRepository;
import com.example.resource_tracker.security.jwt.JwtUtils;
import com.example.resource_tracker.security.services.UserDetailsImpl;
import com.example.resource_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/sign_in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		JwtResponse response = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), roles);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/sign_up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByLogin(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use"));
		}

		UserDto userDto = new UserDto();
		userDto.setEmail(signUpRequest.getEmail());
		userDto.setLogin(signUpRequest.getUsername());
		userDto.setPassword(encoder.encode(signUpRequest.getPassword()));
		userService.addUser(userDto);

		return ResponseEntity.ok(new MessageResponse("User registered successfully"));
	}
}
