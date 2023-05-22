package com.wang.MyBlog.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.MyBlog.Service.ArticleService;
import com.wang.MyBlog.Service.UserService;
import com.wang.MyBlog.entity.User;


@Controller
public class UserController {
	@RequestMapping("/test")
	    public String test(){ 
	        return "/Articles/ArticleList";
	    }
	@Autowired
	private UserService service;
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/index")
	public String turnIndex(HttpSession session)
	{
		session.removeAttribute("userName");
		return "index";
	}
	@PostMapping("/login?logout")
	public String turnindex()
	{
		return "index";
	}
	@GetMapping("/main")
	public String turnMain(Model model)
	{
		model.addAttribute("list",articleService.findAll());
		return "/Users/main";
	}
	
	//转到用户选择注册还是登陆页面
	@GetMapping("/UserPage")
	public String turnUserPage()
	{
		return "/Users/UserPage";
	}
	
	//注册
	@GetMapping("/register")
	public String register(User user,Model model)
	{
		return "/Users/register";
	}
	@PostMapping("/register")
	public String register(@Valid User user,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("msg","注册失败");
			return "/Users/register";
		}
		
		if (service.addUser(user)!=null)
		{
			model.addAttribute("msg","注册成功");
			return "index";
		}
		model.addAttribute("msg","用户名重复，注册失败");
		return "index";
	}
	//用户登陆
	@GetMapping("/Userlogin")
	public String loginVerify()
	{
		return "/Users/Userlogin";
	}
	@PostMapping("/Userlogin")
	public String loginVerify(Model model,User user,HttpSession session)
	{
		if (service.findIdentity(user.getUserName()).equals("用户"))
		{
			if (service.loginVerify(user)==true)
			{
				model.addAttribute("user",user);
				model.addAttribute("list",articleService.findAll());
				session.setAttribute("userName",service.findUserName(user));
				return "/Users/main";
			}
			else
			{
				model.addAttribute("msg","登陆失败，用户名和密码不匹配");
				return "/Users/Userlogin";
			}
		}
		else
		{
			if (service.loginVerify(user)==true)
			{
				model.addAttribute("list",service.findAll());
				session.setAttribute("userName",user.getUserName());
				return "/Admin/UserList";
			}
			else
			{
				model.addAttribute("msg","登陆失败，用户名和密码不匹配");
				return "/Users/Userlogin";
			}
		}
	}
	@GetMapping("/adminmodify")
	public String modifyadmin(Model model,HttpSession session)
	{
		String userName=(String)session.getAttribute("userName");
		model.addAttribute("user",service.findUserByUserName(userName));
		return "/Admin/AdminModify";
	}
	@PostMapping("/adminmodify")
	public String modifyadmin(Model model,User user)
	{
		if (service.ModifyUser(user)!=null)
		{
			model.addAttribute("msg","修改成功");
			model.addAttribute("list",service.findAll());
			return "/Admin/UserList";
		}
		model.addAttribute("msg","修改失败");
		model.addAttribute("list",service.findAll());
		return "/Admin/UserList";
	}
	@GetMapping("/infomodify")
	public String modifyinfo(Model model,HttpSession session)
	{
		String userName=(String)session.getAttribute("userName");
		model.addAttribute("user",service.findUserByUserName(userName));
		return "/Users/InfoModify";
	}
	@PostMapping("/infomodify")
	public String modifyuser(Model model,User user)
	{
		if (service.ModifyUser(user)!=null)
		{
			model.addAttribute("msg","修改成功");
			model.addAttribute("list",service.findAll());
			return "/Users/main";
		}
		model.addAttribute("msg","修改失败");
		model.addAttribute("list",service.findAll());
		return "/Users/main";
	}
	
	
	/*@GetMapping("/userList")
	public String findAllUsers(Model model)
	{
		model.addAttribute("list",service.findAll());
		return "UserList";
	}
	@PostMapping("/userList")
	public String findAllUsers()
	{
		return "UserList";
	}*/
}
