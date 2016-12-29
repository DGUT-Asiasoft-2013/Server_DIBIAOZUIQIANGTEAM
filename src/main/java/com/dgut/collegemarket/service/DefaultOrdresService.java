package com.dgut.collegemarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public Page<Orders> findOrdersPageByBuyerId(Integer id, int page) {
	
		
		Sort sort = new Sort(Direction.DESC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		
		
		return ordersRepo.findOrdersPageByBuyerId(id,pageRequest);
	}

	@Override
	public Page<Orders> findOrdersPageByUserId(Integer id, int publishers_id,
			int page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		
		
		return ordersRepo.findOrdersPageByUserId(id,publishers_id,pageRequest);
	}

	

}
