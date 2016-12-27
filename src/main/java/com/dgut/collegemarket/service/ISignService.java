package com.dgut.collegemarket.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Sign;
import com.dgut.collegemarket.entity.User;


public interface ISignService {
	Sign save(Sign sign);
	Page<Sign> getAllSignsByPage(int pageNum);
	int countTodaySigns(String date);
	int rankingToday(Date todaydate,Date mydate);
	Sign findByUserId(int userId,Date todaydate);
//	Sign findByUserId(int userId,String  todaydate);
}
