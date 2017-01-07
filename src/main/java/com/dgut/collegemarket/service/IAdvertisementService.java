package com.dgut.collegemarket.service;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Advertisement;
import com.dgut.collegemarket.entity.User;


public interface IAdvertisementService {
	Page<Advertisement> getAdvertisements(int page);
}
