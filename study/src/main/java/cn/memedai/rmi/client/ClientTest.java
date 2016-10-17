package cn.memedai.rmi.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.memedai.rmi.server.HelloRMIService;

public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/remote/RMIClient.xml");
		HelloRMIService hms = context.getBean("myClient",HelloRMIService.class);
		System.out.println(hms.add(1, 2));
	}

}
