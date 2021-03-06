package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Contact;
import com.dgut.collegemarket.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IContactRepository extends PagingAndSortingRepository<Contact, Integer>{

	@Query("from Contact contact where contact.user.id = ?1")
	Page<Contact> getContactByUserId(Integer id, Pageable pageable);



}
