package com.item.service;

import com.item.model.User;

public interface UserService {
	
	boolean createUser(User User);
	
	User selectUser(User user);
	
	boolean isUserNameExist(String username);
	
	boolean deleteUser(long id);
}
