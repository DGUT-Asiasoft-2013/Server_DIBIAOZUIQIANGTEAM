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
import com.dgut.collegemarket.entity.OrdersProgress;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IOrdersProgressRepository;
import com.dgut.collegemarket.repository.IOrdersRepository;

@Component
@Service
@Transactional
public class DefaultOrdresProgressService implements IOrdersProgressService {

	@Autowired
	IOrdersProgressRepository ordersProgressRepository;


	@Override
	public OrdersProgress save(OrdersProgress progress) {
		return ordersProgressRepository.save(progress);
	}


	@Override
	public Page<OrdersProgress> findOrdersProgressPageByOrdersId(
			Integer orders_id, int page) {
		Sort sort = new Sort(Direction.ASC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		return ordersProgressRepository.findOrdersProgressPageByOrdersId(orders_id,pageRequest);
	}

	

}
