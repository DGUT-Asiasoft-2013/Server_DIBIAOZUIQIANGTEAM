package com.dgut.collegemarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Admin;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.repository.IAdminRepository;
import com.dgut.collegemarket.repository.IGoodsRepository;

@Component
@Service
@Transactional
public class DefaultGoodsService implements IGoodsService {

	@Autowired
	IGoodsRepository goodsRepo;


	@Override
	public Goods save(Goods goods) {
		return goodsRepo.save(goods);
	}


	@Override
	public Page<Goods> getGoodsPage(int page) {
		
		Sort sort = new Sort(Direction.DESC,"createDate");
        PageRequest pageRequest=new PageRequest(page, 10, sort);
		
		return goodsRepo.findAll(pageRequest);
	}


	@Override
	public Goods findOne(int goods_id) {
		return goodsRepo.findOne(goods_id);
	}
	
	
}
