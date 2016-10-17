package cn.memedai.guava.map;

import com.google.common.collect.Maps;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;

public class ConcurrentLinkedHashMapDemo {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		ConcurrentLinkedHashMap<String, String > map = 
				new ConcurrentLinkedHashMap.Builder().maximumWeightedCapacity(2)
				.listener(new EvictionListener<String, String>() {

					@Override
					public void onEviction(String key, String value) {
						System.out.println("Evicted key=" + key + ", value=" + value);  
						
					}
				}).build();
		map.put("1", "111");
		map.put("2", "222");
		map.put("3", "3333");
		System.out.println(map.get("1"));
		System.out.println(map.get("2"));
		
		
		//Maps.newHashMap();
	}

}
