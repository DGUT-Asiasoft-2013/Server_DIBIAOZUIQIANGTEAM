package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Sign;
import com.dgut.collegemarket.entity.User;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ISignRepository extends PagingAndSortingRepository<Sign, Integer>{

	@Query("select count(*) from Sign sign where DATE_FORMAT(sign.createDate,'%Y-%m-%d') = ?1")
	int countTodaySigns(String date);
	
	@Query("SELECT count(*) FROM Sign sign where sign.createDate >= ?1 and sign.createDate <= ?2")
	int rankingToday(Date todaydate,Date mydate);
	
	@Query("from Sign sign where sign.user.id = ?1 and sign.createDate >= ?2")
	Sign findByUserId(int userId,Date todaydate);
}
