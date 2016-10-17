package cn.memedai.joda;

import org.joda.time.DateTime;

public class Test {

	/**
	 * @Description: joda-time使用测试
	 * @param @param args   
	 * @return void  
	 * @throws
	 * @author admin
	 * @date 2016-2-29
	 */
	public static void main(String[] args) {
		// 年,月,日,时,分,秒
		DateTime dateTime = new DateTime(2016,02,29,13,37,15);
		// 年,月,日,时,分,秒,毫秒    
		DateTime date = new DateTime(2016,02,29,13,37,15,333);
		System.out.println(dateTime.toString("MM/dd/yyyy HH:mm:ss.SSS"));
		System.out.println(dateTime.toString("dd/MM/yyyy hh:mm:ss"));
		System.out.println(dateTime.toString("EEE dd MMMM,yyyy HH:mm:ssa"));
		System.out.println(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
		System.out.println("MM/dd/yyyy HH:mm Z");
		System.out.println();
		System.out.println();
	}

}
