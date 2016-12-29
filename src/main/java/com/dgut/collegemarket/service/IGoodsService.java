package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.User;

public interface IGoodsService {
	Goods save(Goods goods);

	Page<Goods> getGoodsPage(int i);

	Goods findOne(int goods_id);
	
}
