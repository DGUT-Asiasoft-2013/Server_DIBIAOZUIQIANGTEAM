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
import com.dgut.collegemarket.entity.OrdersProgress;

@Repository
public interface IOrdersProgressRepository extends PagingAndSortingRepository<OrdersProgress, Integer>{
	

	@Query("from OrdersProgress ordersProgress where ordersProgress.orders.id = ?1")	
	Page<OrdersProgress> findOrdersProgressPageByOrdersId(Integer orders_id,Pageable pageable);


}
