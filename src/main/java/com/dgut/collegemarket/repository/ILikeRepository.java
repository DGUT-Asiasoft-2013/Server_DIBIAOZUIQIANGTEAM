package com.dgut.collegemarket.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.Admin;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.Likes;
import com.dgut.collegemarket.entity.Likes.Key;

@Repository
public interface ILikeRepository extends PagingAndSortingRepository<Likes, Integer>{

	
	@Query("select count(*) from Likes likes where likes.id.post.id = ?1")
	int countLikes(int postId);
	
	@Query("select count(*) from Likes likes where likes.id = ?1")
	int judgeLikes(Key id);
}
