package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Contact;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.OrdersProgress;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IContactService;
import com.dgut.collegemarket.service.IGoodsService;
import com.dgut.collegemarket.service.IOrdersProgressService;
import com.dgut.collegemarket.service.IOrdersService;
import com.dgut.collegemarket.service.IUserService;

@RestController
@RequestMapping("/api/orders/progress")
public class OrdersProgressAPIController {

	@Autowired
	IUserService userService;

	@Autowired
	IOrdersService ordersService;

	@Autowired
	IOrdersProgressService ordersProgressService;
	
	/**
	 * 找到当前用户 
	 * @param request
	 * @return user
	 */
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OrdersProgress addOrdersProgress(@RequestParam int orders_id,
			@RequestParam String  content, @RequestParam String title, @RequestParam int state,
		 HttpServletRequest request) {

		Orders orders=new Orders();
		orders.setId(orders_id);
		OrdersProgress progress = new OrdersProgress();
		progress.setContent(content);
		progress.setTitle(title);
		progress.setOrders(orders);
		progress = ordersProgressService.save(progress);
		orders=ordersService.findOne(orders_id);
		orders.setState(state);
		ordersService.save(orders);
		return progress;
	}

	@RequestMapping(value = "/byOrdersId/{page}", method = RequestMethod.POST)
	public Page<OrdersProgress> findOrdersProgressPageByOrdersId(@PathVariable int page,@RequestParam int orders_id,
			HttpServletRequest request) {

		return ordersProgressService.findOrdersProgressPageByOrdersId(orders_id, page);
	}



	
	
}
