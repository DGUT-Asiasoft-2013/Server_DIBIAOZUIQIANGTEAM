package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Contact;
import com.dgut.collegemarket.entity.User;


public interface IContactService {
	Contact save(Contact contact);

	Page<Contact> getContactByUserId(User currentUser, int page);

}
