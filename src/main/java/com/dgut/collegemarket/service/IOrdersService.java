package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.User;

public interface IOrdersService {
	Orders save(Orders orders);


	Page<Orders> findOrdersPageByBuyerId(User buyer);

}
