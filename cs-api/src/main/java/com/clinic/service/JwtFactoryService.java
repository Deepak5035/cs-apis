package com.clinic.service;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;

import com.clinic.entity.LoginEntity;
import com.clinic.model.JwtModel;


public interface JwtFactoryService {

	JwtModel generateJWT(LoginEntity loginCredentialsEntity, String transactionId, RsaJsonWebKey senderJwk);

	JwtModel generateRefreshJWT(LoginEntity loginCredentialsEntity, String transactionId, RsaJsonWebKey senderJwk);

	public boolean validateToken(RsaJsonWebKey senderJwk, String token, Long id, String source, String resource,
			String ip, HttpServletRequest req) ;

}
