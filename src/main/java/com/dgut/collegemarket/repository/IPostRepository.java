package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<Post, Integer>{

	@Query("from Post post where post.id = ?1")
	Page<Post> getPostByPostId(Integer id,Pageable pageable);
}
