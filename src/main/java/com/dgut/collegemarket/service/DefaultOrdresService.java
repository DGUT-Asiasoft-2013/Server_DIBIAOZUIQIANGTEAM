package com.dgut.collegemarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IOrdersRepository;

@Component
@Service
@Transactional
public class DefaultOrdresService implements IOrdersService {

	@Autowired
	IOrdersRepository ordersRepo;

	//ÐÂ½¨¶©µ¥
	@Override
	public Orders save(Orders orders) {
		
		
		return ordersRepo.save(orders);
	}

	@Override
	public Page<Orders> findOrdersPageByBuyerId(User buyer) {
		// TODO Auto-generated method stub
		return null;
	}

}
