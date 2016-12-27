package com.dgut.collegemarket.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Admin;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Orders;

@Repository
public interface IOrdersRepository extends PagingAndSortingRepository<Orders, Integer>{


}
