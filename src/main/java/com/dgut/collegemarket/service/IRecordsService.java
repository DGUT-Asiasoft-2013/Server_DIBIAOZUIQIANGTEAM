package com.dgut.collegemarket.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.User;


public interface IRecordsService {

	 Records save(Records records) ;

	 Page<Records> getRecordsByUserId(User currentUser, int page);
	

}
