package cn.memedai.io.aio;

import org.apache.commons.lang3.ArrayUtils;

public class TimeClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8080;
		
		if(ArrayUtils.isNotEmpty(args)){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				//采用默认值
			}
		}
		
		new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"AIO-AsyncTimeClientHandler-001").start();
	}

}
