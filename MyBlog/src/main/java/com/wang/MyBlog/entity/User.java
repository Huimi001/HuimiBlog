package com.wang.MyBlog.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User 
{
	public User()
	{
		
	}
	@Id
	@GeneratedValue
	private int userId;
	
	@Column(length=50)
	@NotBlank
	@Size(min=3,max=10)
	private String userName;
	
	@Column(length=30)
	private String password;
	
	@Column(length=30)
	@NotBlank
	private String fullName;
	
	@Column(length=30)
	private String email;
	
	@Column(length=30)
	private String phone;
	
	@Column(length=30)
	private String wechat;
	
	@Column(length=100)
	private String config;
	
	@Column(length=30)
	private String role;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;
	
	@Transient
	private int age;

	private LocalDateTime createDateTime=LocalDateTime.now();
	
	private boolean enabled=true;
	
	@Column(length=2)
	private String sex;
	
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean getEnabled() {
		return enabled;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return this.birthday.getYear()-LocalDate.now().getYear();
	}

}
