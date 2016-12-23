package com.dgut.collegemarket.service;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.PostComment;
import com.dgut.collegemarket.repository.IPostCommentRepository;
import com.dgut.collegemarket.repository.IPostRepository;


@Component
@Service
@Transactional
public class DefaultPostCommentService implements IPostCommentService {


	@Autowired
	IPostCommentRepository iPostResp;

	@Override
	public Page<PostComment> getPostCommentsByPostId(Integer postId,Integer page) {
		Sort sort = new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return iPostResp.findAllByPostCommentId(postId, pageRequest);
	}

	@Override
	public PostComment save(PostComment post) {
		return iPostResp.save(post);
	}

	@Override
	public PostComment findOne(Integer id) {
		return iPostResp.findOne(id);
	}
	
	

}
