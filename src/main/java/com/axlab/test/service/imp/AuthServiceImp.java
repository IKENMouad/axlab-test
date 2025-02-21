package com.axlab.test.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axlab.test.config.JwtUtil;
import com.axlab.test.enums.Role;
import com.axlab.test.model.User;
import com.axlab.test.repository.UserRepository;
import com.axlab.test.request.AuthRequest;
import com.axlab.test.response.AuthResponse;
import com.axlab.test.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public User register(User user) {
		try {
			if (userRepository.existsByUsername(user.getUsername()))
				throw new RuntimeException("Account already exist with the provided email");

			if (user.getRole() == null)
				user.setRole(Role.USER);

			String hashedPass = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPass);

			return userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public AuthResponse login(AuthRequest authRequest) throws Exception {
		try {

			final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.username());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

			return new AuthResponse(jwtUtil.generateToken(userDetails), userDetails.getUsername());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
