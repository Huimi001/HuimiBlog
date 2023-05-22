package com.wang.MyBlog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.MyBlog.entity.Admin;
import com.wang.MyBlog.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	public boolean loginVerify(Admin admin){
		if(adminRepository.findByAdminName(admin.getAdminName())!=null)
		{
			return true;	
		}
		return false;
	}
}
