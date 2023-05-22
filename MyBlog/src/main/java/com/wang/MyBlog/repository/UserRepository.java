package com.wang.MyBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.wang.MyBlog.entity.User;

//定义实体类和主键类型
public interface UserRepository extends JpaRepository<User, Integer> {
	//User findByUserNameContainingAndNameStartingWith(String userName,String name);
	@Query(value="select userName from User where userName like %:userName%")
	String findByUserName(@Param("userName")String userName);
	
	@Query(value="select enabled from User where userName like %:userName%")
	boolean findEnabledByUserName(@Param("userName")String userName);
	
	@Query(value="select u from User u")
	List<User> getAllUsers();
	
	@Modifying
	@Query("delete from User where id = ?1")
	void deleteByUserId(Long id);

	@Query(value="select password from User where userName like %:userName%")
	String findPasswordByUserName(@Param("userName")String userName);
	
	@Query(value="select u from User u where userId=?1")
	User findUser(int userId);
	
	@Query(value="select role from User where userName=?1")
	String findIdentity(String userName);
	
	@Query(value="select u from User u where userName=?1")
	User findUserByUserName(String userName);
}
