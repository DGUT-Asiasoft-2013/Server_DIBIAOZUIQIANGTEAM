package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer>{

	@Query("from User u where u.account = ?1")
	User findUserByAccount(String account);

	@Query("from User u where u.name like %?1%")
	Page<User> searchUserWithKeyword(String keyword, Pageable  page);
}
