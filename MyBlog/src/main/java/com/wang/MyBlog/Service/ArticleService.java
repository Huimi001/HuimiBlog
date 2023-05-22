package com.wang.MyBlog.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.Type;
import com.wang.MyBlog.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public Article findByNewsId(int id)
	{
		return articleRepository.findBynewsId(id);
	}
	public List<Article> findAll()
	{
		return articleRepository.findAll();
	}
	
	public void deleteById(int id)
	{
		articleRepository.deleteById(id);
	}
	
	public Article addNews(Article news){
		return articleRepository.save(news);
	}
	
	public Article ModifyNews(Article news)
	{
			Article article =articleRepository.getOne(news.getNewsId());
			article.setNewsTitle(news.getNewsTitle());
			article.setContent(news.getContent());
			//先查询出来，不用执行save方法，就能保存
			return article;
	}
	public Article getArticleById(int id){
		return articleRepository.showArticle(id);
	}
	
	public List<Article> findByTypeId(Type type)
	{
		return articleRepository.findByTypeId(type);
	}
	
	/*public List<Type> getAllType(){
		return typeRepository.getAllType();
	}*/
}
