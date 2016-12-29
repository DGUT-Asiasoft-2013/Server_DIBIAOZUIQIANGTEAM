package com.dgut.collegemarket.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgut.collegemarket.entity.Likes;
import com.dgut.collegemarket.entity.Likes.Key;
import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.User;


public interface ILikesService {
	public int countLikes(int postId);
	public Likes save(Likes likes);
	public Likes findOne(Key id);
	public void delete(Likes likes);
	public boolean judge(Key id);
}
