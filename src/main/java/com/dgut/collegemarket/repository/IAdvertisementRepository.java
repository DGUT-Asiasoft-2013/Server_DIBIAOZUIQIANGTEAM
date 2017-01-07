package com.dgut.collegemarket.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Admin;
import com.dgut.collegemarket.entity.Advertisement;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Likes;
import com.dgut.collegemarket.entity.Likes.Key;

@Repository
public interface IAdvertisementRepository extends PagingAndSortingRepository<Advertisement, Integer>{

	
	
}
