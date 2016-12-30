package com.dgut.collegemarket.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Message;

@Repository
public interface IMessageRepository extends
		PagingAndSortingRepository<Message, Integer> {

	@Query("from Message message where message.sender.id = ?1 and message.receiver.id=?2")
	Page<Message> findAllMessages(int sender_id, int receiver_id,
			Pageable pageable);

}
