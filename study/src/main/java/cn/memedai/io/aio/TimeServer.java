package cn.memedai.io.aio;

import org.apache.commons.lang3.ArrayUtils;


public class TimeServer {

	public static void main(String[] args) {
		int port = 8080;
		
		if(ArrayUtils.isNotEmpty(args)){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//采用默认值
			}
		}
		
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
