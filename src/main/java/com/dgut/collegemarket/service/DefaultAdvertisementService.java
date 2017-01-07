package com.dgut.collegemarket.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Advertisement;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IAdvertisementRepository;
import com.dgut.collegemarket.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultAdvertisementService implements IAdvertisementService {

	@Autowired
	IAdvertisementRepository advertisementRepo;

	@Override
	public Page<Advertisement> getAdvertisements(int page) {
		Sort sort = new Sort(Direction.DESC, "editDate");
		PageRequest pageRequest = new PageRequest(page, 50, sort);
		return advertisementRepo.findAll(pageRequest);
	}
	
	

}
