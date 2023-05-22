package com.wang.MyBlog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.MyBlog.entity.Article;
import com.wang.MyBlog.entity.User;
import com.wang.MyBlog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user)
	{
		if (userRepository.findByUserName(user.getUserName())==null)
			return userRepository.save(user);
		return null;
	}
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	public String findIdentity(String userName)
	{
		return userRepository.findIdentity(userName);
	}
	public boolean loginVerify(User user){
		if(userRepository.findByUserName(user.getUserName()).equals(user.getUserName()))
		{
			if(userRepository.findPasswordByUserName(user.getUserName()).equals(user.getPassword()))
				if(userRepository.findEnabledByUserName(user.getUserName())==true)
					return true;	
		}
		return false;
	}
	public String findUserName(User user)
	{
		return userRepository.findByUserName(user.getUserName());
	}
	public void deleteById(int id)
	{
		userRepository.deleteById(id);
	}
	
	public User findUser(int userId)
	{
		return userRepository.findUser(userId);
	}

	public User ModifyUser(User user)
	{
			User u =userRepository.getOne(user.getUserId());
			u.setUserName(user.getUserName());
			u.setBirthday(user.getBirthday());
			u.setFullName(user.getFullName());
			u.setPassword(user.getPassword());
			u.setSex(user.getSex());
			//先查询出来，不用执行save方法，就能保存
			return u;
	}
	
	public User rightchange(int userId)
	{
			User u =userRepository.getOne(userId);
			u.setEnabled(false);
			//先查询出来，不用执行save方法，就能保存
			return u;
	}
	public User statechange(int userId)
	{
			User u =userRepository.getOne(userId);
			u.setEnabled(true);
			//先查询出来，不用执行save方法，就能保存
			return u;
	}

	public User findUserByUserName(String userName) {
		User u=userRepository.findUserByUserName(userName);
		return u;
	}
}
