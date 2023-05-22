package com.wang.MyBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wang.MyBlog.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query(value="select AdminName from Admin where AdminName like %:AdminName%")
	String findByAdminName(@Param("AdminName")String AdminName);
	
}
