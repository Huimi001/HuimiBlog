package com.wang.MyBlog.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.Type;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query(value="select a from Article a")
	List<Article> getAllArticles();
	
	//删除文章
	@Modifying
	@Query("delete from Article where newsId = ?1")
	void deleteBynewsId(int id);
	
	//查看文章
	@Query(value="select a from Article a where newsId like %:newsId%")
	Article showArticle(@Param("newsId")int id);
	
	//修改文章
	@Modifying
	@Query("update Article Set newsTitle=?1,createDate=?2,Content=?3 where newsId=?4")
	void modifyArticle(String newsTitle,LocalDateTime createDate,String Content,int id);
	
	//添加文章 这里好像有问题
	/*@Transactional
	@Modifying
	@Query(value="insert into Article (newsTitle,createDate,Content,type) values(?1,?2,?3,?4)")
	int addArticle(String newsTitle,LocalDateTime createDate,String Content,Type type);
	*/
	 
	 
	@Query(value="select a from Article a where newsId = ?1")
	Article findBynewsId(int newsId);
	
	@Query(value="select a from Article a where type = ?1")
	List<Article> findByTypeId(Type type);
}
