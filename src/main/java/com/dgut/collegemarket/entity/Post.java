package com.dgut.collegemarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.DateRecord;

@Entity
public class Post extends DateRecord{
	User publishers;//发布者
	String title;//标题
	String content;//内容
	String albums;//帖子图集
	double reward;//报酬
	boolean issolve;//是否解决
	int browseVolume;//帖子的浏览量
	
	@ManyToOne(optional=false)
	public User getPublishers() {
		return publishers;
	}
	public void setPublishers(User publishers) {
		this.publishers = publishers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAlbums() {
		return albums;
	}
	public void setAlbums(String albums) {
		this.albums = albums;
	}
	public double getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	public boolean isIssolve() {
		return issolve;
	}
	public void setIssolve(boolean issolve) {
		this.issolve = issolve;
	}
	public int getBrowseVolume() {
		return browseVolume;
	}
	public void setBrowseVolume(int browseVolume) {
		this.browseVolume = browseVolume;
	}
	
	

	
}
