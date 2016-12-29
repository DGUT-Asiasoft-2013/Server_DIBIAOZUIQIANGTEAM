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
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IContactService;
import com.dgut.collegemarket.service.IGoodsService;
import com.dgut.collegemarket.service.IOrdersService;
import com.dgut.collegemarket.service.IUserService;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {

	@Autowired
	IUserService userService;

	@Autowired
	IOrdersService ordersService;

	@Autowired
	IContactService contactService;

	@Autowired
	IGoodsService goodsService;

	/**
	 * 找到当前用户
	 * 
	 * @param request
	 * @return user
	 */
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Orders addOrdres(@RequestParam int goods_id,
			@RequestParam int contact_id, @RequestParam double price,
			@RequestParam int quantity, @RequestParam String note,
			@RequestParam boolean isPayOnline, HttpServletRequest request) {

		Goods goods = goodsService.findOne(goods_id);
		Contact contact = contactService.findOne(contact_id);

		Orders orders = new Orders();
		orders.setBuyer(getCurrentUser(request));
		orders.setGoods(goods);
		orders.setContact(contact);
		orders.setPrice(price);
		orders.setQuantity(quantity);
		orders.setNote(note);
		orders.setPayOnline(isPayOnline);
		orders = ordersService.save(orders);
		return orders;
	}

	@RequestMapping(value = "/my/buy/{page}")
	public Page<Orders> findOrdersPageByBuyerId(@PathVariable int page,
			HttpServletRequest request) {

		User user = getCurrentUser(request);

		return ordersService.findOrdersPageByBuyerId(user.getId(), page);
	}

	@RequestMapping(value = "/my/all/{page}")
	public Page<Orders> findOrdersPageByUserId(@PathVariable int page,
			HttpServletRequest request) {

		User user = getCurrentUser(request);

		return ordersService.findOrdersPageByUserId(user.getId(), user.getId(),
				page);
	}

}
