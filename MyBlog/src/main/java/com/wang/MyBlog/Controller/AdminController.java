package com.wang.MyBlog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wang.MyBlog.Service.AdminService;
import com.wang.MyBlog.Service.UserService;
import com.wang.MyBlog.entity.Admin;
import com.wang.MyBlog.entity.User;


@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	//转到管理员登陆页面
	@GetMapping("/adminLogin")
	public String turnAdminlogin()
	{
		return "/Admin/AdminLogin";
	}
	@PostMapping("/adminLogin")
	public String adminlogin(Model model,Admin admin)
	{
		if (adminService.loginVerify(admin)==true)
		{	
			model.addAttribute("list",userService.findAll());
			return "/Admin/UserList";
		}
		else
		{
			model.addAttribute("msg","登陆失败，用户名和密码不匹配");
			return "/Admin/AdminLogin";
		}
	}
	//转到管理员登陆页面
	@GetMapping("/userList")
	public String TurnUserList(Model model)
	{
		model.addAttribute("list",userService.findAll());
		return "/Admin/UserList";
	}
	//删除用户
	@GetMapping("/userdelete")
	public String deleteUser(Model model,int userId)
	{
		userService.deleteById(userId);
		model.addAttribute("msg","删除成功");
		model.addAttribute("list",userService.findAll());
		return "/Admin/UserList";
	}
	//修改用户
	@GetMapping("/usermodify")
	public String turnUserModify(Model model,int userId)
	{
		model.addAttribute("user",userService.findUser(userId));
		return "/Admin/UserModify";
	}
	@PostMapping("/usermodify")
	public String modifyuser(Model model,User user)
	{
		if (userService.ModifyUser(user)!=null)
		{
			model.addAttribute("msg","修改成功");
			model.addAttribute("list",userService.findAll());
			return "/Admin/UserList";
		}
		model.addAttribute("msg","修改失败");
		model.addAttribute("list",userService.findAll());
		return "/Admin/UserList";
	}
	
	@GetMapping("/rightchange")
	public String turnRightChange(Model model,int userId)
	{
		if(userService.rightchange(userId)!=null)
		{	
			model.addAttribute("msg","账号封禁成功");
			model.addAttribute("list",userService.findAll());
			return "/Admin/UserList";
		}
		model.addAttribute("list",userService.findAll());
		model.addAttribute("msg","账号封禁失败");
		return "/Admin/UserList";
	}
	@GetMapping("/useropen")
	public String stateChange(Model model,int userId)
	{
		if(userService.statechange(userId)!=null)
		{	
			model.addAttribute("msg","账号启用成功");
			model.addAttribute("list",userService.findAll());
			return "/Admin/UserList";
		}
		model.addAttribute("list",userService.findAll());
		model.addAttribute("msg","账号启用失败");
		return "/Admin/UserList";
	}
	
	@GetMapping("/addUser")
	public String addUser()
	{
		return "/Admin/AddUser";
	}
	@PostMapping("/addUser")
	public String addUser(Model model,User user)
	{
		if (userService.addUser(user)!=null)
		{
			model.addAttribute("msg","添加成功");
			model.addAttribute("list",userService.findAll());
			return "/Admin/UserList";
		}
		model.addAttribute("msg","添加失败");
		model.addAttribute("list",userService.findAll());
		return "/Admin/UserList";
	}
}
