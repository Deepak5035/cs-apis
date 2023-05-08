package com.clinic.utility;

public class ConstantManager {

	
	private String description ;
	
	private int statusCode;
	
	
	public ConstantManager(String description, int statusCode) {
		super();
		this.description = description;
		this.statusCode = statusCode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public static final ConstantManager UserAlreadyExists = new ConstantManager("User Already Exists" , 403);
	public static final ConstantManager AccountCreatedSuccessfully = new ConstantManager("Account Created For User Successfully" , 200);
	public static final ConstantManager UserNotExists = new ConstantManager("User Not Exists" , 404);
	public static final ConstantManager LoginSucessfull = new ConstantManager("Login Successfull For User" , 200);
	public static final ConstantManager IncurrectCredentials = new ConstantManager("Credentials Are Not Correct" , 401);
	public static final ConstantManager Successfull = new ConstantManager("Successfull" , 200);
	public static final ConstantManager NoRecdordFound = new ConstantManager("No Record Found" , 404);
	public static final ConstantManager ServerSideError = new ConstantManager("Exception On Server Side" , 500);
	public static final ConstantManager UnAuhorization = new ConstantManager("Exception On Server Side" , 401);
	
}
