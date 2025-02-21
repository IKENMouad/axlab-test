package com.axlab.test.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axlab.test.model.User;
import com.axlab.test.request.AuthRequest;
import com.axlab.test.response.AuthResponse;
import com.axlab.test.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/register")
	public User account(@RequestBody @Valid User user) {
		return authService.register(user);
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody @Valid AuthRequest authRequest) throws Exception {
		return authService.login(authRequest);
	}

}
