package cn.memedai.rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("/remote/RMIServer.xml");
	}

}
