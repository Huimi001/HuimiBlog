package com.wang.MyBlog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wang.MyBlog.Service.TypeService;
import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.Type;

@Controller

public class TypeController {
	@Autowired
	private TypeService service;
	
	@GetMapping("/addType")
	public String addType(Model model)
	{
		model.addAttribute("list",service.findAll());
		return "/Type/AddType";
	}
	@PostMapping("/addType")
	public String addType(Model model,String typename)
	{
		if (service.addType(typename)!=null)
		{
			model.addAttribute("msg","添加成功");
			return "/Type/AddType";
		}
		model.addAttribute("msg","添加失败");
		return "/Type/AddType";
	}
	@GetMapping("/typedelete")
	public String deleteType(Model model,int typeId)
	{
		service.deletetype(typeId);
		model.addAttribute("msg","禁用成功 ");
		model.addAttribute("list",service.findAll());
		return "/Type/AddType";
	}
}
