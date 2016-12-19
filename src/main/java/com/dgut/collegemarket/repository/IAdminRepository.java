package com.dgut.collegemarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Admin;

@Repository
public interface IAdminRepository extends PagingAndSortingRepository<Admin, Integer>{

}
