package com.wang.MyBlog.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private int commentID;
	
	@Column(length=50)
	private String commentName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDate=LocalDateTime.now();
	
	@Column(length=500)
	private String commentContent;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Article article;
	
	@Column(length=50)
	private Integer parentID;
	
	@Column(length=50)
	private String parentName;
	
	@Transient
    private Comment parentComment;
    
	@Transient
	private List<Comment> replyComments = new ArrayList<>();
	
	public List<Comment> getReplyComments() {
		return replyComments;
	}

	public void setReplyComments(List<Comment> replyComments) {
		this.replyComments = replyComments;
	}

	public int getParentID() {
		return parentID;
	}
	
	public Comment getParentComment() {
		return parentComment;
	}



	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}



	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}



	public String getParentName() {
		return parentName;
	}



	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	public int getCommentID() {
		return commentID;
	}



	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}



	public String getCommentName() {
		return commentName;
	}



	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	public String getCommentContent() {
		return commentContent;
	}



	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}



	public Article getArticle() {
		return article;
	}



	public void setArticle(Article article) {
		this.article = article;
	}



	public Comment() 
	{
		
	}

}
