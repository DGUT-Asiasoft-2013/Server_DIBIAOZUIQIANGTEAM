package com.dgut.collegemarket.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultUserService implements IUserService {

	@Autowired
	IUserRepository userRepo;
	
	@Override
	public void login(String account, String passwordHash) {
		

	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePassword(String newPasswordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userRepo.findOne(id);
	}

	@Override
	public User findByAccount(String account) {
		return userRepo.findUserByAccount(account);
	}

	@Override
	public Page<User> searchUserWithKeyword(String keyword, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 50, sort);
		return userRepo.searchUserWithKeyword(keyword, pageRequest);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

}
