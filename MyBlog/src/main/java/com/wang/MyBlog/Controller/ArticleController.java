package com.wang.MyBlog.Controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wang.MyBlog.Service.ArticleService;
import com.wang.MyBlog.Service.CommentService;
import com.wang.MyBlog.Service.TypeService;
import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.Comment;
import com.wang.MyBlog.entity.Type;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService service;
	@Autowired
	private TypeService typeService;
	@Autowired
	private CommentService commentService;
	/*@GetMapping("/AddArticle")
	public String addArticles(Model model)
	{
		//model.addAttribute("list",service.getAllType());
		return "/Users/register";
	}*/
	@GetMapping("/addArticle")
	public String TurnAddArticle(Model model)
	{
		model.addAttribute("list",typeService.findAll());
		return "/Articles/AddArticles";
	}
	@PostMapping("/addArticle")
	public String addArticle(Model model,@Valid Article news,int typeId)
	{
		Type type=typeService.findBytypeId(typeId);
		news.setType(type);
		if (service.addNews(news)!=null)
		{
			model.addAttribute("msg","发布成功");
			model.addAttribute("list",service.findAll());
			return "/Articles/ArticleList";
		}
		model.addAttribute("msg","发布失败");
		model.addAttribute("list",service.findAll());
		return "/TurnArticleList";
		
	}
	
	//转到文章列表
	@GetMapping("/TurnArticleList")
	public String findAllArticles(Model model)
	{
		model.addAttribute("list",service.findAll());
		return "/Articles/ArticleList";
	}
	
	//转到文章列表
		@GetMapping("/visitor")
		public String findArticles(Model model)
		{
			model.addAttribute("list",service.findAll());
			return "/Visitor/visitor";
		}
		
	//查看文章详情
	@GetMapping("/articledetail")
	public String showdetail(Model model,int newsId)
	{
		List<Comment> comments = commentService.listComment(newsId);
	    model.addAttribute("comments", comments);
	    model.addAttribute("article",service.findByNewsId(newsId));
	    return "/Articles/ArticleDetail";
		
		/*model.addAttribute("article",service.findByNewsId(newsId));
		model.addAttribute("comments",commentService.getCommentById(newsId));
		List<Comment> c=commentService.getCommentById(newsId);
		
		return "/Articles/ArticleDetail";*/
	}
	//删除文章
	@GetMapping("/articledelete")
	public String deleteArticle(Model model,int newsId)
	{
		service.deleteById(newsId);
		model.addAttribute("msg","删除成功");
		model.addAttribute("list",service.findAll());
		return "/Articles/ArticleList";
	}
	//修改文章
	@GetMapping("/articlemodify")
	public String turnArticleModify(Model model,int newsId)
	{
		model.addAttribute("list",typeService.findAll());
		model.addAttribute("article",service.findByNewsId(newsId));
		return "/Articles/ModifyArticle";
	}
	@PostMapping("/articlemodify")
	public String modifyArticle(Model model,Article news)
	{
		if (service.ModifyNews(news)!=null)
		{
			model.addAttribute("msg","修改成功");
			model.addAttribute("list",service.findAll());
			return "/Articles/ArticleList";
		}
		model.addAttribute("msg","修改失败");
		model.addAttribute("list",service.findAll());
		return "/Articles/ArticleList";
		
	}
	
	/*
	@PostMapping("/addArticle")
	public String addArticle(Model model,Article news)
	{
		if (service.addNews(news))
		{
			model.addAttribute("msg","发布成功");
			return "/Articles/ArticleList";
		}
		model.addAttribute("msg","发布失败");
		return "/Articles/ArticleList";
		
	}*/
	
	//登陆用户查询文章
	@PostMapping("/search")
	public String findLimitArticles(Model model,String typename)
	{
		Type t=typeService.findBytypeName(typename);
		model.addAttribute("list",service.findByTypeId(t));
		return "/Articles/SearchResult";
	}
	//登陆用户查询文章
	@PostMapping("/visitorsearch")
	public String findvistorArticles(Model model,String typename)
	{
		Type t=typeService.findBytypeName(typename);
		model.addAttribute("list",service.findByTypeId(t));
		return "/Visitor/SearchResult1";
	}
	@GetMapping("/vistordetail")
	public String detail(Model model,int newsId)
	{
		List<Comment> comments = commentService.listComment(newsId);
	    model.addAttribute("comments", comments);
	    model.addAttribute("article",service.findByNewsId(newsId));
	    return "/Visitor/ArticleDetail";
	    
		/*model.addAttribute("article",service.findByNewsId(newsId));
		model.addAttribute("comments",commentService.getCommentById(newsId));
		List<Comment> c=commentService.getCommentById(newsId);
		
		return "/Articles/ArticleDetail";*/
	}
}
