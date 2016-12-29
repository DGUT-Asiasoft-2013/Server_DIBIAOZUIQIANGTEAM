package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Likes;
import com.dgut.collegemarket.entity.Likes.Key;
import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.ILikesService;
import com.dgut.collegemarket.service.IPostService;
import com.dgut.collegemarket.service.IUserService;


@RestController
@RequestMapping("/api/likes")
public class LikesAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ILikesService iLikesService;
	
	@Autowired
	IPostService iPostService;
	/**
	 * 找到当前用户
	 * @param request
	 * @return user
	 */
	public User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
		
	/**
	 * 点赞
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addlikes",method = RequestMethod.POST)
	public int addLikes(
			@RequestParam int postId,
			HttpServletRequest request){
		Likes likes = new Likes();
		Key key = new Key();
		Post post = iPostService.findOne(postId);
		
		key.setSubscribers(getCurrentUser(request));
		key.setPost(post);

		likes.setId(key);
		iLikesService.save(likes);
		
		
		return iLikesService.countLikes(postId);
	}
	/**
	 * 取消点赞
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletelikes",method = RequestMethod.POST)
	public int deleteLikes(
			@RequestParam int postId,
			HttpServletRequest request){
		Likes likes = new Likes();
		Key key = new Key();
		Post post = iPostService.findOne(postId);
		
		key.setSubscribers(getCurrentUser(request));
		key.setPost(post);

		likes.setId(key);
		iLikesService.delete(likes);
		
		
		return iLikesService.countLikes(postId);
	}
	/**
	 * 统计点赞
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/countlikes",method = RequestMethod.POST)
	public int countLikes(
			@RequestParam int postId){
		return iLikesService.countLikes(postId);
	}
	/**
	 * 判断是否点赞
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/judgelikes",method = RequestMethod.POST)
	public boolean judgeLikes(
			@RequestParam int postId,
			HttpServletRequest request){
		Key key = new Key();
		key.setPost(iPostService.findOne(postId));
		key.setSubscribers(getCurrentUser(request));
		
		return iLikesService.judge(key);
	}
}
