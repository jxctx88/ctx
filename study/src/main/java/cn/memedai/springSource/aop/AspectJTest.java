package cn.memedai.springSource.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJTest {

	@Pointcut("execution(* *.test(..))")
	public void test(){
		
	}
	
	@Before("test()")
	public void beforeTest(){
		System.out.println("beforeTest");
	}
	
	@After("test()")
	public void afterTest(){
		System.out.println("afterTest");
	}
	
	@Around("test()")
	public Object arountTest(ProceedingJoinPoint p){
		System.out.println("around_before");
		Object o = null;
		try{
			o = p.proceed();
		}catch(Throwable e){
			e.printStackTrace();
		}
		System.out.println("around_after");
		return o;
	}
}
