package com.yesun.sample.equals;

/**
 * sample Student.java
 * Description: 
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2013年11月17日 Create.
 * ChangeLog:
 */
public class Student {
	
	public Student(String id, String name) {
		this.username = name;
		this.id = id;
	}
	
	private String id;
	private String username;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* 
	 * 重写基于id的hashcode
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		System.out.println("hashCode");
		return this.id.hashCode();
	}
	
	
	
	/* 
	 * 基于id判断重复
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		Student other = (Student)obj;
		if(this.id.equals(other.getId())){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	

}
