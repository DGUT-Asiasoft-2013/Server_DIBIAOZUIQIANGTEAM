package com.dgut.collegemarket.service;

import com.dgut.collegemarket.entity.User;

public interface IUserService {
	User create(String account, String passwordHash);
	
	void login(String account, String passwordHash);
	User getCurrentUser();
	boolean changePassword(String newPasswordHash);
	void logout();
}
