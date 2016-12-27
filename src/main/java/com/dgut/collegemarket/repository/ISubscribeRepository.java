package com.dgut.collegemarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.Subscriber;
import com.dgut.collegemarket.entity.Subscriber.Key;
import com.dgut.collegemarket.entity.User;

@Repository
public interface ISubscribeRepository extends PagingAndSortingRepository<Subscriber, Subscriber.Key>{

	@Query("select count(*) from Subscriber subscriber where subscriber.id.publishers.id = ?1")
	int subscribeCountsOfSubscriber(int publishers_id);

	@Query("select count(*) from Subscriber subscriber where subscriber.id.subscribers.id = ?1 and subscriber.id.publishers.id = ?2")
	int checkSubscribeExsists(Integer id, int publishers_id);

	@Query("from Subscriber subscriber where subscriber.id.publishers.id = ?1")
	Page<Subscriber> getPublishersByUserId(Integer id, Pageable pageable);

	@Query("from Subscriber subscriber where subscriber.id.subscribers.id = ?1")
	Page<Subscriber> getSubscribersByUserId(Integer id, Pageable pageable);
	
	
}