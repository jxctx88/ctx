package cn.memedai.btrace;

import java.util.Random;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
			Random random = new Random();
			execute(random.nextInt(4000));
		}
	}

	public static Integer execute(int sleepTime) {
		try{
			Thread.sleep(sleepTime);
		}catch(Exception e){
			
		}
		System.out.println("sleep time is=>" + sleepTime);
		return 0;
	}

}
