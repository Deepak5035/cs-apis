package com.clinic.service.impl;

import java.util.Date;

import org.jose4j.jwk.RsaJsonWebKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.LoginServiceDao;
import com.clinic.dao.TokenDetailsRepo;
import com.clinic.entity.LoginEntity;
import com.clinic.entity.TokenEntity;
import com.clinic.model.JwtModel;
import com.clinic.model.StatusDescription;
import com.clinic.request.LoginRequest;
import com.clinic.response.LoginResponse;
import com.clinic.service.LoginService;
import com.clinic.utility.ConstantManager;
import com.clinic.utility.DateTimeHelperUtils;
import com.clinic.utility.PasswordEncryptionDescryption;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginServiceDao loginServiceDao;

	@Autowired
	PasswordEncryptionDescryption passwordEncryptionDescryption;

	@Autowired
	JwtFactoryServiceImpl jwtFactoryServiceImpl;

	@Autowired
	TokenDetailsRepo TokenDetailsRepo;

	@Override
	public LoginResponse newUserLogin(LoginRequest loginRequest) {

		StatusDescription statusDescription = new StatusDescription();
		LoginResponse response = new LoginResponse();

		try {

			LoginEntity loginEntity = loginServiceDao.findByMobileNumber(loginRequest.getMobileNumber());

			if (loginEntity != null) {
				statusDescription.setDescription(ConstantManager.UserAlreadyExists.getDescription());
				statusDescription.setCode(ConstantManager.UserAlreadyExists.getStatusCode());
				response.setStatusDescription(statusDescription);

			} else {

				LoginEntity loginEntity1 = new LoginEntity();
				loginEntity1.setName(loginRequest.getName());
				String encriptedPass = passwordEncryptionDescryption.passwordEncrypt(loginRequest.getPassword());
				loginEntity1.setPassword(encriptedPass);
				loginEntity1.setMobileNumber(loginRequest.getMobileNumber());
				loginEntity1.setEmailId(loginRequest.getEmailId());

				statusDescription.setDescription(ConstantManager.AccountCreatedSuccessfully.getDescription());
				statusDescription.setCode(ConstantManager.AccountCreatedSuccessfully.getStatusCode());
				response.setStatusDescription(statusDescription);

				loginServiceDao.save(loginEntity1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public LoginResponse checkuserLogin(LoginRequest loginRequest, RsaJsonWebKey senderJwk) {

		StatusDescription statusDescription = new StatusDescription();
		LoginResponse response = new LoginResponse();

		try {

			LoginEntity loginEntity = loginServiceDao.findByMobileNumber(loginRequest.getMobileNumber());

			if (loginEntity == null) {
				loginEntity = loginServiceDao.findByEmailId(loginRequest.getEmailId());
			}

			if (loginEntity != null) {

				Boolean isCorrectPass = passwordEncryptionDescryption.passwordDecrypt(loginEntity.getPassword(),
						loginRequest.getPassword());

				if (isCorrectPass) {

					String transactionId = generateTransactionId();
					JwtModel jwtNewToken = jwtFactoryServiceImpl.generateJWT(loginEntity, transactionId, senderJwk);

					JwtModel jwtRefereshToken = jwtFactoryServiceImpl.generateRefreshJWT(loginEntity, transactionId,
							senderJwk);

					TokenEntity tokenEntity = updateJWTToken(loginEntity, jwtNewToken, jwtRefereshToken, "WEB");

					statusDescription.setDescription(ConstantManager.LoginSucessfull.getDescription());
					statusDescription.setCode(ConstantManager.LoginSucessfull.getStatusCode());
					response.setStatusDescription(statusDescription);
					response.getLoginEntity().setId(loginEntity.getId());
					response.setTokenEntity(tokenEntity);

				} else {
					statusDescription.setDescription(ConstantManager.IncurrectCredentials.getDescription());
					statusDescription.setCode(ConstantManager.IncurrectCredentials.getStatusCode());
					response.setStatusDescription(statusDescription);
				}

			} else {

				statusDescription.setDescription(ConstantManager.UserNotExists.getDescription());
				statusDescription.setCode(ConstantManager.UserNotExists.getStatusCode());
				response.setStatusDescription(statusDescription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String generateTransactionId() {
		int otp = 0;
		try {
			otp = (int) (Math.random() * 9000) + 12163;

		} catch (Exception e) {
			e.printStackTrace();
		}
		Long txnid = System.currentTimeMillis() + otp;
		return "" + txnid;
	}

	private TokenEntity updateJWTToken(LoginEntity loginCredentials, JwtModel jwtNewToken, JwtModel jwtRefereshToken,
			String source) {

		TokenEntity TokenEntity = null;

		try {
			TokenEntity = TokenDetailsRepo.findOneByLid(loginCredentials.getId());

			if (TokenEntity != null && jwtNewToken != null && jwtRefereshToken != null) {

				TokenEntity.setCurrentDate(new Date());
				TokenEntity.setJwtRefreshToken(jwtRefereshToken.getJwtRefreshToken());
				TokenEntity.setRefreshExpiryDate(jwtRefereshToken.getRefreshExpiryDate());
				TokenEntity.setRefreshCurrentDate(new Date());
				TokenEntity.setJwtToken(jwtNewToken.getJwtToken());
				TokenEntity.setExpiryDate(jwtNewToken.getExpiryDate());
				TokenEntity.setTokenKey(jwtNewToken.getTokenKey());
				TokenEntity.setExpireDate(jwtNewToken.getExpiryDate());
				TokenEntity.setSource(source);
				TokenEntity.setRefreshKey(jwtRefereshToken.getRefreshKey());
				TokenEntity.setJwtTokenExpiryDateTime(
						DateTimeHelperUtils.convertDateToString(TokenEntity.getExpiryDate()));
				TokenEntity.setJwtRefreshTokenExpiryDateTime(
						DateTimeHelperUtils.convertDateToString(TokenEntity.getRefreshExpiryDate()));

				TokenEntity.setLoginEntity(loginCredentials);

				TokenDetailsRepo.save(TokenEntity);

				return TokenEntity;

			} else {
				TokenEntity TokenEntity1 = new TokenEntity();

				if (jwtNewToken != null && jwtRefereshToken != null) {

					TokenEntity1.setCurrentDate(new Date());
					TokenEntity1.setJwtRefreshToken(jwtRefereshToken.getJwtRefreshToken());
					TokenEntity1.setRefreshExpiryDate(jwtRefereshToken.getRefreshExpiryDate());
					TokenEntity1.setRefreshCurrentDate(new Date());
					TokenEntity1.setJwtToken(jwtNewToken.getJwtToken());
					TokenEntity1.setExpiryDate(jwtNewToken.getExpiryDate());
					TokenEntity1.setTokenKey(jwtNewToken.getTokenKey());
					TokenEntity1.setExpireDate(jwtNewToken.getExpiryDate());
					TokenEntity1.setSource(source);
					TokenEntity1.setRefreshKey(jwtRefereshToken.getRefreshKey());

					TokenEntity1.setJwtTokenExpiryDateTime(
							DateTimeHelperUtils.convertDateToString(TokenEntity1.getExpiryDate()));
					TokenEntity1.setJwtRefreshTokenExpiryDateTime(
							DateTimeHelperUtils.convertDateToString(TokenEntity1.getRefreshExpiryDate()));
					TokenEntity1.setLoginEntity(loginCredentials);
				}

				TokenDetailsRepo.save(TokenEntity1);
				return TokenEntity1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return TokenEntity;
		}

	}

	public LoginResponse forgotPassword(LoginRequest loginRequest) {

		StatusDescription statusDescription = new StatusDescription();
		LoginResponse loginResponse = new LoginResponse();

		try {
			LoginEntity loginEntity = loginServiceDao.findByEmailId(loginRequest.getEmailId());

			if (loginEntity == null) {
				loginEntity = loginServiceDao.findByMobileNumber(loginRequest.getMobileNumber());
			}

			if (loginEntity != null) {

				String encryptedPass = passwordEncryptionDescryption.passwordEncrypt(loginRequest.getPassword());
				loginEntity.setPassword(encryptedPass);

				loginServiceDao.save(loginEntity);

				statusDescription.setDescription(ConstantManager.PasswordUpdatedSuccessfully.getDescription());
				statusDescription.setCode(ConstantManager.PasswordUpdatedSuccessfully.getStatusCode());
				loginResponse.setStatusDescription(statusDescription);

			} else {
				statusDescription.setDescription(ConstantManager.UserNotExists.getDescription());
				statusDescription.setCode(ConstantManager.UserNotExists.getStatusCode());
				loginResponse.setStatusDescription(statusDescription);
			}

		} catch (Exception e) {
			statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
			statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
			loginResponse.setStatusDescription(statusDescription);
			e.printStackTrace();
		}
		return loginResponse;
	}

	public LoginResponse deleteUser(LoginRequest loginRequest) {

		StatusDescription statusDescription = new StatusDescription();
		LoginResponse loginResponse = new LoginResponse();

		try {
			System.out.println(loginRequest.getMobileNumber());

			Long userId = loginServiceDao.findByMobileNumber(loginRequest.getMobileNumber()).getId();
			System.out.println(userId);

			// delete token
			TokenDetailsRepo.deleteById(TokenDetailsRepo.findOneByLid(userId).getId());

			loginServiceDao.deleteById(userId);

			statusDescription.setCode(ConstantManager.UserDeleted.getStatusCode());
			statusDescription.setDescription(ConstantManager.UserDeleted.getDescription());
			loginResponse.setStatusDescription(statusDescription);

		} catch (Exception e) {
			statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
			statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
			loginResponse.setStatusDescription(statusDescription);
			e.printStackTrace();
		}

		return loginResponse;
	}
}
