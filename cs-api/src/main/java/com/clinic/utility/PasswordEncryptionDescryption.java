package com.clinic.utility;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionDescryption {

	public String passwordEncrypt(String password) {

		String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return encryptedPassword;
	}

	public boolean passwordDecrypt(String encryptedPassword, String password) {
		
		return BCrypt.checkpw(password, encryptedPassword);
	
	}
}
