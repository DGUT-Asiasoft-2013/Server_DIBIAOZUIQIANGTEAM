package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Contact;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IContactService;
import com.dgut.collegemarket.service.IUserService;


@RestController
@RequestMapping("/api/contact")
public class ContactAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IContactService contactService;
	
	
	/**
	 * 找到当前用户
	 * @param request
	 * @return user
	 */
	public User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
		
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Contact register(
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam String school,
			@RequestParam String sushe,
			HttpServletRequest request){
		
		Contact contact = new Contact();
	
		contact.setName(name);
		contact.setPhone(phone);
		contact.setSchool(school);
		contact.setSushe(sushe);
		
		return contactService.save(contact);
	}
	
	
	
}
