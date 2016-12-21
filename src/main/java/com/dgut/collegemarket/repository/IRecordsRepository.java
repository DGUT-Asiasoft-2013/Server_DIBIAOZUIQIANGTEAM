package com.dgut.collegemarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.User;

@Repository
public interface IRecordsRepository extends PagingAndSortingRepository<Records, Integer>{
	
	@Query("from Records records where records.user.id = ?1")
	Page<Records> getRecordsByUserId(Integer id,Pageable pageable);

	
	
}