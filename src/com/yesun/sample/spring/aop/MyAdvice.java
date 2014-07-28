package com.yesun.sample.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
public class MyAdvice{

	public void before(JoinPoint joinpoint){
		System.out.println("AOP：验证权限" + joinpoint.getSignature().getName());
		Object[] args=joinpoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]); //输出传入的参数
        }
//        System.out.println(joinpoint.getSignature().getName()); //调用的方法名
//        System.out.println("---------权限检测--------------");

	}

}
