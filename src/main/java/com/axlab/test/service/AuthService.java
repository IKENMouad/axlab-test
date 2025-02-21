package com.axlab.test.service;

import com.axlab.test.model.User;
import com.axlab.test.request.AuthRequest;
import com.axlab.test.response.AuthResponse;

public interface AuthService {

	User register(User user);

	AuthResponse login(AuthRequest authRequest) throws Exception;
}
