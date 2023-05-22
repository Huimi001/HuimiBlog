package com.wang.MyBlog.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Article {
	
	@Id
	@GeneratedValue
	private int newsId;
	
	@Column(length=50)
	private String newsTitle;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDate=LocalDateTime.now();
	
	@Column(length=500)
	private String Content;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Type type;
	
	@OneToMany(mappedBy="article")
	private Set<Comment> comments=new HashSet<>();
	
	public int getNewsId() {
		return newsId;
	}



	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}



	public String getNewsTitle() {
		return newsTitle;
	}



	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	public String getContent() {
		return Content;
	}



	public void setContent(String content) {
		Content = content;
	}



	



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public Article() {
		
	}

}
