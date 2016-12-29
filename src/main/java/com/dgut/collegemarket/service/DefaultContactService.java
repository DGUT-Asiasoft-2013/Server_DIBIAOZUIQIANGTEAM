package com.dgut.collegemarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Contact;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IContactRepository;

@Component
@Service
@Transactional
public class DefaultContactService implements IContactService {

	@Autowired
	IContactRepository contactRepo;

	@Override
	public Contact save(Contact contact) {
		return contactRepo.save(contact);
	}

	@Override
	public Page<Contact> getContactByUserId(User currentUser, int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		return contactRepo.getContactByUserId(currentUser.getId(),pageRequest);
	}

	@Override
	public Contact findOne(int contact_id) {
		return contactRepo.findOne(contact_id);
	}

}
