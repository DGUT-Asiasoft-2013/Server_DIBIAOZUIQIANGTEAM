package com.dgut.collegemarket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Message;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IMessageRepository;

@Component
@Service
@Transactional
public class DefaultMessageService implements IMessageService {

	@Autowired
	IMessageRepository messageRepo;

	@Override
	public Message save(Message message) {
		
		return messageRepo.save(message);
	}

	@Override
	public Page<Message> getMessageByUserId(User currentUser, User receiver,
			int page) {
		
		Sort sort = new Sort(Direction.ASC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		
		return messageRepo.findAllMessages(currentUser.getId(),receiver.getId(),pageRequest);
	}
	
	
}
