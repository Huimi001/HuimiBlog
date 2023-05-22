package com.wang.MyBlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Admin {
	@Id
	@GeneratedValue
	private int adminId;
	
	@Column(length=50)
	@NotBlank
	@Size(min=3,max=10)
	private String AdminName;
	
	@Column(length=30)
	private String password;
	
	
	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return AdminName;
	}


	public void setAdminName(String adminName) {
		this.AdminName = adminName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Admin() 
	{
		
	}

}
