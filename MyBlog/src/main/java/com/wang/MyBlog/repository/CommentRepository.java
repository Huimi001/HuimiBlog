package com.wang.MyBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wang.MyBlog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value="select c from Comment c where ARTICLE_NEWS_ID = ?1 and PARENTID=null")
	List<Comment> findBynewsId(int newsId);
	
	@Query(value="select c from Comment c where parentID = ?1")
	List<Comment> findByParentIdNotNull(int id);
}
