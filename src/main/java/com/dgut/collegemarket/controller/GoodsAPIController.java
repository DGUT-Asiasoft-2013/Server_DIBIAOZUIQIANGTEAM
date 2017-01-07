package com.dgut.collegemarket.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.collegemarket.entity.Advertisement;
import com.dgut.collegemarket.entity.Goods;
import com.dgut.collegemarket.entity.User;
import com.dgut.collegemarket.service.IAdvertisementService;
import com.dgut.collegemarket.service.IGoodsService;
import com.dgut.collegemarket.service.IUserService;


@RestController
@RequestMapping("/api/goods")
public class GoodsAPIController {

	@Autowired
	 IUserService userService;

	@Autowired
	IGoodsService goodsService;
	
	@Autowired
	IAdvertisementService advertisementService;

	/**
	 * 找到当前用户
	 * @param request
	 * @return user
	 */
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public  User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
		

	/**
	 * 添加一条商品信息
	 * @param title
	 * @param content
	 * @param quantity
	 * @param price
	 * @param albums
	 * @param request
	 * @return Goods
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Goods addGoods(
			@RequestParam String title,
			@RequestParam String content,
			@RequestParam int quantity,
			@RequestParam double price,
			MultipartFile albums,
			HttpServletRequest request){
		
	Goods goods=new Goods();
	goods.setTitle(title);
	goods.setContent(content);
	goods.setQuantity(quantity);
	goods.setPrice(price);
	goods.setPublishers(getCurrentUser(request));
	goods=goodsService.save(goods);
	
		if(albums!=null){
			try{
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/goods");
				FileUtils.copyInputStreamToFile(albums.getInputStream(), new File(realPath,goods.getId()+".png"));
				goods.setAlbums("upload/goods/"+goods.getId()+".png");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return goodsService.save(goods);
	}
	
	@RequestMapping(value = "/all/{page}")
	public Page<Goods> getGoodsPage(@PathVariable int page) {
		return goodsService.getGoodsPage(page);
	}
	
	
	@RequestMapping(value = "/all/advertisement/{page}")
	public Page<Advertisement> getAdvertisementPage(@PathVariable int page) {
		return advertisementService.getAdvertisements(page);
	}
	
}
