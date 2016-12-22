package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<User, Integer>{

	
}
