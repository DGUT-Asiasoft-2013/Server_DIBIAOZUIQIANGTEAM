package com.dgut.collegemarket.service;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Sign;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.ISignRepository;
import com.dgut.collegemarket.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultSignService implements ISignService {

	@Autowired
	ISignRepository signRepo;

	@Override
	public Sign save(Sign sign) {
		return signRepo.save(sign);
	}

	@Override
	public Page<Sign> getAllSignsByPage(int pageNum) {
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(pageNum, 20,sort);
		return signRepo.findAll(pageRequest);
	}

	@Override
	public int countTodaySigns(String date) {
		return signRepo.countTodaySigns(date);
	}

	@Override
	public int rankingToday(Date todaydate, Date mydate) {
		return signRepo.rankingToday(todaydate, mydate);
	}

	@Override
	public Sign findByUserId(int userId, Date todaydate) {
		return signRepo.findByUserId(userId, todaydate);
	}
//	@Override
//	public Sign findByUserId(int userId, String todaydate) {
//		return signRepo.findByUserId(userId, todaydate);
//	}
	
	

}
