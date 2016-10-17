package cn.memedai.javabase;

import java.util.HashMap;

public class HashMapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=1;i<100;i++){
			map.put(i+"", "xiong"+i);
		}
		System.out.println(map.get("1"));
	}

}
