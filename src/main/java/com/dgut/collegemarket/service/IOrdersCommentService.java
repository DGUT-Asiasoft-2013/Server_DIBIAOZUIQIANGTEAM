package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.OrdersComment;
import com.dgut.collegemarket.entity.User;

public interface IOrdersCommentService {
	OrdersComment save(OrdersComment ordersComment);
	Page<OrdersComment> getOrderComment(int orderCommentId,int page);
	Page<OrdersComment> findAllByGoodsId(Integer goodsId,int page);
}
