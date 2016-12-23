package com.dgut.collegemarket.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.PostComment;
import com.dgut.collegemarket.entity.User;


public interface IPostCommentService {
	public Page<PostComment> getPostCommentsByPostId(Integer postId,Integer page);
	public PostComment save(PostComment post);
	public PostComment findOne(Integer id);
}
