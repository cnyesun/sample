package com.yesun.sample.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ArrayListSample {

	private ArrayList<String> courses = new ArrayList<String>();
	
	public void addCourse(String courseName) {
		courses.add(courseName);
	}

	public List<String> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	

	public static void main(String[] args) {
		ArrayListSample al = new ArrayListSample();
		al.addCourse("yesun");
//		al.getCourses().add("dcm");
		
		
		//000001AF
		String aaa = Integer.toHexString(431);//.toHexString(Integer.valueOf("431", 16));
		String a = "A";
		int b = Integer.parseInt(a, 16);
		System.out.println(b);
		


	}

}
