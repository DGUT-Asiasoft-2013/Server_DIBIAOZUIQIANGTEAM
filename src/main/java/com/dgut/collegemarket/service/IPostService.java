package com.dgut.collegemarket.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.User;


public interface IPostService {
	public Page<Post> getPosts(Integer page);
	public Post save(Post post);
	public Post findOne(Integer id);
}
