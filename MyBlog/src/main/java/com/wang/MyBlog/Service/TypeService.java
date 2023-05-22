package com.wang.MyBlog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.MyBlog.entity.Type;
import com.wang.MyBlog.repository.TypeRepository;


@Service
public class TypeService {
	@Autowired
	private TypeRepository typeRepository;
	
	public Type addType(String typename)
	{
		Type type=new Type();
		type.setType(typename);
		return typeRepository.save(type);
	}
	
	public List<Type> findAll()
	{
		return typeRepository.findAll();
	}

	public Type findBytypeId(int typeId) {
		return typeRepository.findBytypeId(typeId);
	}
	
	public Type findBytypeName(String typename) {
		return typeRepository.findBytypeName(typename);
	}

	public void deletetype(int typeId) {
		typeRepository.deleteById(typeId);
	}
	
}
