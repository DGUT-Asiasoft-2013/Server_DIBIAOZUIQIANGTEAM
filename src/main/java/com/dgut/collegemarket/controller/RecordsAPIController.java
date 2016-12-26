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
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IRecordsService;
import com.dgut.collegemarket.service.IUserService;

@RestController
@RequestMapping("/api/record")
public class RecordsAPIController {

	@Autowired
	IUserService userService;

	@Autowired
	IRecordsService recordsService;
//	
//	@Autowired
//	ISubscribeService subscribeService;

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

	public Records addRecords(User user, String cause, double coin,
			HttpServletRequest request) {
		User me = getCurrentUser(request);
		Records records = new Records();
		records.setCause(cause);
		records.setUser(me);
		records.setCoin(coin);

		records = recordsService.save(records);

		if (records != null) {
			user.setCoin(user.getCoin() + coin);
			userService.save(user);
		}

		return records;

	}

	@RequestMapping(value ="/records/{page}")
	public Page<Records> getRecordsByUserId(@PathVariable int page,
			HttpServletRequest request) {

		return recordsService.getRecordsByUserId(getCurrentUser(request), page);
	}

	@RequestMapping(value ="/records")
	public Page<Records> getRecordsByUserId(HttpServletRequest request) {
		return recordsService.getRecordsByUserId(getCurrentUser(request), 0);
	}

	@RequestMapping(value ="/records/recharge",method=RequestMethod.POST)
	public Records recharge(@RequestParam double coin,@RequestParam String cause,
			HttpServletRequest request){
	
		return addRecords(getCurrentUser(request), cause, coin, request);
	}
	
	@RequestMapping("/search/{keyword}")
	public Page<User> searchUserWithKeyword (
		@PathVariable String keyword,
		@RequestParam(defaultValue = "0") int page){
		return userService.searchUserWithKeyword(keyword,page);
	}
	
//	@RequestMapping("/isSubscribed/{publishers_id}")
//	public boolean checkSubscribed(@PathVariable int publishers_id,
//			HttpServletRequest request) {
//		User me = getCurrentUser(request);
//		return subscribeService.checkSubscribed(me.getId(), publishers_id);
//	}
//
//	@RequestMapping("/subscribe/{publishers_id}")
//	public int countSubscribes(@PathVariable int publishers_id) {
//		return subscribeService.countSubscribers(publishers_id);
//	}
//
//	@RequestMapping(value = "/subscribe/{publishers_id}", method = RequestMethod.POST)
//	public int changeSubscribes(@PathVariable int publishers_id,
//			@RequestParam boolean subscribe, HttpServletRequest request) {
//		User me = getCurrentUser(request);
//		User publishers = userService.findById(publishers_id);
//
//		if (subscribe) {
//			subscribeService.addSubscriber(me, publishers);
//		} else {
//			subscribeService.removeSubscriber(me, publishers);
//		}
//		return subscribeService.countSubscribers(publishers_id);
//	}
}
