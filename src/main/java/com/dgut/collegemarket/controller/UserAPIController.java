package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IRecordsService;
import com.dgut.collegemarket.service.IUserService;


@RestController
@RequestMapping("/api/user")
public class UserAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IRecordsService recordsService;

	/**
	 * 用户注册
	 * @param account
	 * @param passwordHash
	 * @param email
	 * @param name
	 * @param avatar
	 * @param request
	 * @return	user
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public User register(
			@RequestParam String account,
			@RequestParam String passwordHash){
		User user = new User();
		user.setAccount(account);
		user.setPasswordHash(passwordHash);
		user.setName("用户名");
		user.setCoin(0);
		user.setEmail("邮箱地址");
		user.setXp(0);
		
		return userService.save(user);
	}
	
	/**
	 * 用户的登录
	 * @param account
	 * @param passwordHash
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public User login(
			@RequestParam String account,
			@RequestParam String passwordHash,
			HttpServletRequest request){
		User user = userService.findByAccount(account);
		if(user!=null && user.getPasswordHash().equals(passwordHash)){
			HttpSession session = request.getSession(true);
			session.setAttribute("uid", user.getId());
			return user;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取到当前用户
	 * @param request
	 * @return user
	 */
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
		
	/**
	 * 用户上传头像
	 * @param avatar
	 * @param request
	 * @return	user
	 */
	@RequestMapping(value="/update/avatar", method=RequestMethod.POST)
	public void UpdateAvatar(
			MultipartFile avatar,
			HttpServletRequest request){
		if(avatar!=null){
			try{
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/user");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath,getCurrentUser(request).getId() + ".png"));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 用户修改密码
	 * @param avatar
	 * @param request
	 * @return	user
	 */
	@RequestMapping(value="/update/password", method=RequestMethod.POST)
	public User UpdatePassword(
			@RequestParam String newpassword,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		user.setPasswordHash(newpassword);
		return userService.save(user);
	}

	/**
	 * 用户忘记密码
	 * @param avatar
	 * @param request
	 * @return	user  user/forget/password
	 */
	@RequestMapping(value="/forget/password", method=RequestMethod.POST)
	public User ForgetPassword(
			@RequestParam String email,
			@RequestParam String newpassword){
		
		User user = userService.findByEmail(email);
		if(user==null){
			return null;
		}
		user.setPasswordHash(newpassword);
		return userService.save(user);
	}
	
	/**
	 * 修改用户名称
	 * @param username
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update/username", method=RequestMethod.POST)
	public User UpdateUserName(
			@RequestParam String username,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		if(user==null){
			return null;
		}
		user.setName(username);
		return userService.save(user);
	}
}
