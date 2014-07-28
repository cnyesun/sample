package com.yesun.sample.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class AnnotationSample {
	
	@View(Method="test", Value="hahahahahah")
	public void test() {
		System.out.println("In test method.");
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{

		AnnotationSample annotationSample = new AnnotationSample();
		Method method = annotationSample.getClass().getMethod("test");
		System.out.println(method.isAnnotationPresent(View.class));
		method.invoke(annotationSample);
		View view = method.getAnnotation(View.class);
		System.out.println(view.Method());
		System.out.println(view.Value());
		
		System.out.println(System.getProperty("user.dir"));
	}

	
}
