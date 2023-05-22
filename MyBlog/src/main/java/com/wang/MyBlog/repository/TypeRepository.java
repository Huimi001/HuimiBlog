package com.wang.MyBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wang.MyBlog.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
	@Query(value="select t from Type t")
	List<Type> getAllType();
	
	@Query(value="select t from Type t where typeId = ?1")
	Type findBytypeId(int typeId);
	
	@Query(value="select t from Type t where TYPENAME like %:typename%")
	Type findBytypeName(@Param("typename")String typename);
}
