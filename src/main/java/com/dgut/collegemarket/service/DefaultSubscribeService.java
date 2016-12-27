package com.dgut.collegemarket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.Subscriber;
import com.dgut.collegemarket.entity.Subscriber.Key;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.ISubscribeRepository;


@Component
@Service
@Transactional
public class DefaultSubscribeService implements ISubscribeService{

	@Autowired
	ISubscribeRepository subscribeRepo;

	@Override
	public Records save(Records records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Records> getRecordsByUserId(User currentUser, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkSubscribed(Integer id, int publishers_id) {
		return subscribeRepo.checkSubscribeExsists(id, publishers_id)>0;
	}

	@Override
	public void addSubscriber(User subscribers, User publishers) {
		Subscriber.Key key = new Key();
		key.setSubscribers(subscribers);
		key.setPublishers(publishers);

		Subscriber lk = new Subscriber();
		lk.setId(key);
		subscribeRepo.save(lk);
	}

	@Override
	public void removeSubscriber(User me, User publishers) {
		Subscriber.Key key = new Key();
		key.setSubscribers(me);
		key.setPublishers(publishers);

		subscribeRepo.delete(key);
	}

	@Override
	public int countSubscribers(int publishers_id) {
		return subscribeRepo.subscribeCountsOfSubscriber(publishers_id);
	}

	@Override
	public Page<Subscriber> getPublishersByUserId(User currentUser, int page) {
		
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 50, sort);
		
		
		return subscribeRepo.getPublishersByUserId(currentUser.getId(),pageRequest);
	}
	
	@Override
	public Page<Subscriber> getSubscribersByUserId(User currentUser, int page) {
		
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 50, sort);
		
		
		return subscribeRepo.getSubscribersByUserId(currentUser.getId(),pageRequest);
	}


	
}
