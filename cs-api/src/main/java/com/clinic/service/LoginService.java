package com.clinic.service;

import org.jose4j.jwk.RsaJsonWebKey;

import com.clinic.request.LoginRequest;
import com.clinic.response.LoginResponse;

public interface LoginService {

	public LoginResponse newUserLogin(LoginRequest loginRequest);
	
	
	public LoginResponse checkuserLogin(LoginRequest loginRequest , RsaJsonWebKey senderJwk);
}
