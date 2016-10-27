package cn.memedai.springSource.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		TestBean bean = (TestBean)bf.getBean("test");
		bean.test();
	}

}




























