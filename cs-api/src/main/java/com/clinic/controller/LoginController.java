package com.clinic.controller;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.request.LoginRequest;
import com.clinic.response.LoginResponse;
import com.clinic.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/login")
public class LoginController {

	static RsaJsonWebKey senderJwk = null;

	static {
		try {
			senderJwk = RsaJwkGenerator.generateJwk(2048);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@PostMapping(value ="/l1/newUser")
	public ResponseEntity<LoginResponse> newUserLogin(@RequestBody LoginRequest loginRequest){
		
		LoginResponse loginResponse = new LoginResponse();
		
		loginResponse = loginServiceImpl.newUserLogin(loginRequest);
		
		return new ResponseEntity<LoginResponse>(loginResponse , HttpStatus.OK);
	}
	
	@PostMapping(value = "/l2/useLogin")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest loginRequest){
		
		LoginResponse loginResponse = new LoginResponse();
		 
		loginResponse = loginServiceImpl.checkuserLogin(loginRequest , senderJwk);
		
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
		
	}
}
