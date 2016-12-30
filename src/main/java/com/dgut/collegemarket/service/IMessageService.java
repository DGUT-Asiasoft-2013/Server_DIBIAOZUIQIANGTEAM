package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Message;
import com.dgut.collegemarket.entity.Subscriber;
import com.dgut.collegemarket.entity.User;

public interface IMessageService {
	Message save(Message message);

	Page<Message> getMessageByUserId(User currentUser, User receiver,
			int page);
	
}
