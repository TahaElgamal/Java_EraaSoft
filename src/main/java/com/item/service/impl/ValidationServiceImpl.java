package com.item.service.impl;

import com.item.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean isUserNameValid(String userName) {
		if(userName != null && userName.length() >= 4 && !userName.contains(" ")) {
	            return true;
	        }
		 return false;
	}
	

	@Override
	public boolean isPasswordValid(String password) {
		
		String pattern ="^(?=.*[A-Z])(?=.*\\d).{8,}$";

	        if(password.matches(pattern)) {
	            return true;
	        }
	        return false;
	}

	
	@Override
	public boolean isEmailValid(String email) {
		
		String pattern ="^[A-Za-z0-9+_.-]+@(.+)$";
		
	        if(email.matches(pattern)) {
	            return true;
	        }
	        return false;
	}

	
	@Override
	public boolean isAgeValid(int age) {
		
		if(age >= 18 && age <= 60 && age!=0) {
            return true;
        }
        return false;
	}

	
	@Override
	public boolean isPhonNumberValid(String phoneNumber) {
		String pattern ="^01[0125][0-9]{8}$";

		        if(phoneNumber.matches(pattern)) {
		            return true;
		        }
		        return false;
	}

}
