package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Orders;
import com.dgut.collegemarket.entity.Post;
import com.dgut.collegemarket.entity.PostComment;
import com.dgut.collegemarket.entity.Records;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.repository.IPostRepository;
import com.dgut.collegemarket.service.IPostCommentService;
import com.dgut.collegemarket.service.IPostService;
import com.dgut.collegemarket.service.IRecordsService;
import com.dgut.collegemarket.service.IUserService;


@RestController
@RequestMapping("/api/post")
public class PostAPIController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IPostService postService;
	
	@Autowired
	IPostCommentService postCommentService;
	
	@Autowired
	IRecordsService recordsService;

	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public @ResponseBody String hello(){
		return "HELLO  CollegeMarket!";
	}
	
	/**
	 *
	 * @param requestPost
	 * @return user
	 */
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
		
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/addpost", method=RequestMethod.POST)
	public Post addPost(
			@RequestParam String title,
			@RequestParam String content,
			@RequestParam double reward,
			MultipartFile albums,
			HttpServletRequest request){
		Post post = new Post();
		post.setPublishers(getCurrentUser(request));
		post.setTitle(title); 
		post.setContent(content);
		post.setReward(reward);
		post.setIssolve(false);
		post.setBrowseVolume(0);
		postService.save(post);
		
		User user = getCurrentUser(request);
		user.setCoin(user.getCoin()-reward);
		userService.save(user);
		
		Records records = new Records();
		records.setUser(user);
		records.setCoin(reward);
		records.setCause("发帖("+title+")花费了");
		recordsService.save(records);
		if(albums!=null){
			try{
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/post");
				FileUtils.copyInputStreamToFile(albums.getInputStream(), new File(realPath,post.getId()+".png"));
				post.setAlbums("upload/post/"+post.getId()+".png");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return postService.save(post);
	}
	
	/**
	 *
	 * @return
	 */
	@RequestMapping(value="/getposts/{page}")
	public Page<Post> getPosts(
			@PathVariable int page){
		return postService.getPosts(page);
	}
	
	
	
	/**
	 *
	 * @return
	 */
	@RequestMapping(value="/{postId}/publish/postcomment",method=RequestMethod.POST)
	public PostComment publishPostComment(
			@RequestParam String content,
			@PathVariable int postId,
			HttpServletRequest request){
		PostComment postComment = new PostComment();
		postComment.setContent(content);
		postComment.setCommentUser(getCurrentUser(request));
		Post post = postService.findOne(postId);
		postComment.setPost(post);
		return postCommentService.save(postComment);
	}
	
	/**
	 *
	 * @return
	 */
	@RequestMapping(value="/postcomment/{page}",method=RequestMethod.POST)
	public Page<PostComment> getPostComments(
			@PathVariable int page,
			@RequestParam int postId){
		
		return postCommentService.getPostCommentsByPostId(postId,page);
	}
	
	/**
	 * 閲囩撼璇勮
	 * @param accepterId
	 * @param postId
	 * @return
	 */
	@RequestMapping(value="/postcomment/accept",method=RequestMethod.POST)
	public Post AcceptPostComments(
			@RequestParam int accepterId,
			@RequestParam int postId){
		System.out.println(accepterId);
		System.out.println(postId);
		Post post =  postService.findOne(postId);
		post.setIssolve(true);
		post.setAccepterId(accepterId);
		
		User acceptuser = postCommentService.findOne(accepterId).getCommentUser();
		acceptuser.setCoin(post.getReward()+acceptuser.getCoin());
		userService.save(acceptuser);
		User user=	userService.save(acceptuser);
		addRecords(user,"文章评论被采纳"+" 赚取了",post.getReward());
		
//		User publishuser = post.getPublishers();
//		publishuser.setCoin(publishuser.getCoin()-post.getReward());
//		userService.save(publishuser);
		
		return postService.save(post);
	}
	
	public Records addRecords(User user, String cause, double coin
			) {
		Records records = new Records();
		records.setCause(cause);
		records.setUser(user);
		records.setCoin(coin);
		records = recordsService.save(records);
		return records;

	}

	
	@RequestMapping(value="/findByUserId/{page}",method=RequestMethod.POST)
	public Page<Post> findPostPageByUserId(	@PathVariable int page,
			@RequestParam int publishers_id
		){
		return postService.findPostPageByUserId(publishers_id,page);
	}
	
	@RequestMapping(value="/findByUserId",method=RequestMethod.POST)
	public Post findPostByUserId(@RequestParam int publishers_id
		){
		
		Page<Post> postPage=findPostPageByUserId(0,publishers_id);
		if(postPage==null|| postPage.getContent().size()==0)
		return null;
		return postPage.getContent().get(0);
	}
	
}
