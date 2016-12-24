package com.dgut.collegemarket.service;


import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.Subscriber;
import com.dgut.collegemarket.entity.User;


public interface ISubscribeService {

	 Records save(Records records) ;

	 Page<Records> getRecordsByUserId(User currentUser, int page);

	boolean checkSubscribed(Integer id, int publishers_id);

	User findOne(int publishers_id);

	void addSubscriber(User me, User publishers);

	void removeSubscriber(User me, User publishers);

	int countSubscribers(int publishers_id);



	

}
