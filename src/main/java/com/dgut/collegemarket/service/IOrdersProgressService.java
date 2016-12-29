package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.OrdersProgress;
import com.dgut.collegemarket.entity.User;

public interface IOrdersProgressService {
	
	OrdersProgress save(OrdersProgress progress);


	Page<OrdersProgress> findOrdersProgressPageByOrdersId(Integer orders_id, int page);



}
