package com.dgut.collegemarket.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.Subscriber;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.ISubscribeService;
import com.dgut.collegemarket.service.IUserService;

@RestController
@RequestMapping("/api/subscribe")
public class SubscriberAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ISubscribeService subscribeService;

	/**
	 * 找到当前用户
	 * 
	 * @param request
	 * @return user
	 */
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
	
	@RequestMapping(value ="/checkSub/{page}")
	public Page<Subscriber> getSubscribersByUserId(@PathVariable int page,
			HttpServletRequest request) {

		return subscribeService.getSubscribersByUserId(getCurrentUser(request), page);
	}

	@RequestMapping(value ="/checkSub")
	public Page<Subscriber> getSubscribersByUserId(HttpServletRequest request) {
		return subscribeService.getSubscribersByUserId(getCurrentUser(request), 0);
	}
	
	@RequestMapping(value ="/checkPub/{page}")
	public Page<Subscriber> getPublishersByUserId(@PathVariable int page,
			HttpServletRequest request) {

		return subscribeService.getPublishersByUserId(getCurrentUser(request), page);
	}

	@RequestMapping(value ="/checkPub")
	public Page<Subscriber> getPublishersByUserId(HttpServletRequest request) {
		return subscribeService.getPublishersByUserId(getCurrentUser(request), 0);
	}
	
	@RequestMapping("/isSubscribed/{publishers_id}")
	public boolean checkSubscribed(@PathVariable int publishers_id,
			HttpServletRequest request) {
		User me = getCurrentUser(request);
		return subscribeService.checkSubscribed(me.getId(), publishers_id);
	}

	@RequestMapping("/{publishers_id}")
	public int countSubscribes(@PathVariable int publishers_id) {
		return subscribeService.countSubscribers(publishers_id);
	}

	@RequestMapping(value = "/{publishers_id}", method = RequestMethod.POST)
	public int changeSubscribes(@PathVariable int publishers_id,
			@RequestParam boolean subscribe, HttpServletRequest request) {
		User me = getCurrentUser(request);
		User publishers = userService.findById(publishers_id);

		if (subscribe) {
			subscribeService.addSubscriber(me, publishers);
		} else {
			subscribeService.removeSubscriber(me, publishers);
		}
		return subscribeService.countSubscribers(publishers_id);
	}
}
