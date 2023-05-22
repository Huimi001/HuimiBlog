package com.wang.MyBlog.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.wang.MyBlog.Service.ArticleService;
import com.wang.MyBlog.Service.CommentService;
import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.Comment;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	
	@PostMapping("/addComment")
	public String addComment(Model model,@Valid Comment comment,int newsID)
	{
		Article news=articleService.findByNewsId(newsID);
		comment.setArticle(news);
		if (commentService.addComment(comment)!=null)
		{
			model.addAttribute("msg","评论发表成功");
			List<Comment> comments = commentService.listComment(newsID);
		    model.addAttribute("comments", comments);
		    model.addAttribute("article",articleService.findByNewsId(newsID));
		    return "/Articles/ArticleDetail";
		}
		model.addAttribute("msg","发布失败");
		return "/TurnArticleList";
		
	}
	
}
