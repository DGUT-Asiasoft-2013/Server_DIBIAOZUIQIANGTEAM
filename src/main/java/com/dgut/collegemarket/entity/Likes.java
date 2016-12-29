package com.dgut.collegemarket.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;




/**
 * @author 泽恩
 *点赞表
 */
@Entity
public class Likes{

	
	@Embeddable
	public static class Key implements Serializable {
		Post post;
		User subscribers;//点赞者
		
	
		
		@ManyToOne(optional = false)
		public User getSubscribers() {
			return subscribers;
		}

		public void setSubscribers(User subscribers) {
			this.subscribers = subscribers;
		}
		@ManyToOne(optional=false)
		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}
		

	}

	Key id;
	
	Date createDate;//点赞时间


	@EmbeddedId
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}
	
	

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@PrePersist
	void onPrePersist(){
		createDate = new Date();
	}

}
