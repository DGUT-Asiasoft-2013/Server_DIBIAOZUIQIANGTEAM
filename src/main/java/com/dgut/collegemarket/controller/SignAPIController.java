package com.dgut.collegemarket.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Sign;
import com.dgut.collegemarket.entity.SignMsg;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.ISignService;
import com.dgut.collegemarket.service.IUserService;



@RestController
@RequestMapping("/api/sign")
public class SignAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ISignService signService;
	
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
		
	/**
	 * 签到
	 * @return sign
	 */
	@RequestMapping(value="/addsign",method=RequestMethod.POST)
	public Sign addSign(
			@RequestParam int xp,
			HttpServletRequest request){
		Sign sign = new Sign();
		sign.setXp(xp);
		if(getCurrentUser(request)==null){
			return null;
		}
		User user = getCurrentUser(request);
		user.setXp(user.getXp()+xp);
		userService.save(user);
		
		sign.setUser(user);
		return signService.save(sign);
	}
	
	/**
	 * 获取当天签到列表
	 * @return sign
	 */
	@RequestMapping(value="/getallsigns/{pageNum}")
	public SignMsg getAllSigns(
			@PathVariable int pageNum,
			HttpServletRequest request){
		SignMsg sm = new SignMsg();
		sm.setPage(signService.getAllSignsByPage(pageNum));
		
		String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		sm.setCount(signService.countTodaySigns(date));
		Calendar c1 = new GregorianCalendar();
	    c1.set(Calendar.HOUR_OF_DAY, 0);
	    c1.set(Calendar.MINUTE, 0);
	    c1.set(Calendar.SECOND, 0);
	    
		Sign sign =null;
//		String d = "2016-12-27 17:56:23";
		String d = c1.getTime().toLocaleString();
		
		Date dateparam = null ;
		try {
			dateparam = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(d);
			sign = signService.findByUserId(getCurrentUser(request).getId(),dateparam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sign!=null){
			sm.setRanking(signService.rankingToday(dateparam, sign.getCreateDate()));
		}else{
			sm.setRanking(0);
		}
		return sm;
	}
	
}
