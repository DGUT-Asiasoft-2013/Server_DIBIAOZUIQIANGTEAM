package com.dgut.collegemarket.repository;

import org.springframework.stereotype.Repository;

import com.dgut.collegemarket.entity.PostComment;
import com.dgut.collegemarket.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IPostCommentRepository extends PagingAndSortingRepository<PostComment, Integer>{

	@Query("find PostComment postcomment where postcomment.post.id = ?1")
	public Page<PostComment> findAllByPostCommentId(Integer postId,Pageable pageable);
}
