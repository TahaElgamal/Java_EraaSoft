package com.item.service;

public interface ValidationService {
	
	boolean isUserNameValid(String username);
	
	boolean isPasswordValid(String password);
	
	boolean isEmailValid(String email);
	
	boolean isAgeValid(int age);
	
	boolean isPhonNumberValid(String phoneNumber);

}
