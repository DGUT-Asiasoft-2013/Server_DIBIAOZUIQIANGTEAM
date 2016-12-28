package com.dgut.collegemarket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Admin;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;

@Repository
public interface IOrdersRepository extends PagingAndSortingRepository<Orders, Integer>{
	
	@Query("from Orders orders where orders.buyer.id = ?1")	
	Page<Orders> findOrdersPageByBuyerId(Integer id, Pageable pageable);

	@Query("from Orders orders where orders.buyer.id = ?1 or orders.goods.publishers.id =?2")	
	Page<Orders> findOrdersPageByUserId(Integer buyer_id,Integer publishers_id, Pageable pageable);

}
