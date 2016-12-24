package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.User;


public interface IUserService {
	User save(User user);
	void login(String account, String passwordHash);
	User getCurrentUser();
	boolean changePassword(String newPasswordHash);
	void logout();
	User findById(Integer id);
	User findByAccount(String account);
	Page<User> searchUserWithKeyword(String keyword, int page);
}
