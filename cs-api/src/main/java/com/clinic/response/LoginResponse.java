package com.clinic.response;

import com.clinic.entity.LoginEntity;
import com.clinic.entity.TokenEntity;
import com.clinic.model.StatusDescription;

public class LoginResponse {

     private StatusDescription statusDescription;
     
     private LoginEntity loginEntity = new LoginEntity();
     
     private TokenEntity tokenEntity;
     
	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public LoginEntity getLoginEntity() {
		return loginEntity;
	}

	public void setLoginEntity(LoginEntity loginEntity) {
		this.loginEntity = loginEntity;
	}

	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}

	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}
	
}
