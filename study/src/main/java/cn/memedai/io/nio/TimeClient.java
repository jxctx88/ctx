package cn.memedai.io.nio;

import org.apache.commons.lang3.ArrayUtils;


public class TimeClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			port = Integer.valueOf(args[0]);
		}
		
		new Thread(new TimeClientHandle("127.0.0.1",port)).start();
		
	}

}
