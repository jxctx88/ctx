package cn.memedai.io.nio;

import org.apache.commons.lang3.ArrayUtils;

public class TimeServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		
		new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();

	}

}



















