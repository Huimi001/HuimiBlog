package com.wang.MyBlog.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Type {
	
	@Id
	@GeneratedValue
	private int typeId;

	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(length=40)
	private String typename;
	
	@OneToMany(mappedBy="type")
	private Set<Article> articles=new HashSet<>();
	

	public String getTypename() {
		return typename;
	}

	public void setType(String typename) {
		this.typename = typename;
	}

	public Type() {
		// TODO Auto-generated constructor stub
	}

}





