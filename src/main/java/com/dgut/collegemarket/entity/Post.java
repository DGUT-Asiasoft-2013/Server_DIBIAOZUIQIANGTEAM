package com.dgut.collegemarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.DateRecord;

@Entity
public class Post extends DateRecord{
	User publishers;//������
	String title;//����
	String content;//����
	String albums;//����ͼ��
	int quantity;//��Ʒ����
	double reward;//����
	boolean issolve;//�Ƿ���
	
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	

	
}
