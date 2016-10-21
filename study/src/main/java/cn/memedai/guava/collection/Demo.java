package cn.memedai.guava.collection;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(); 
		List<String> list2 = Lists.newArrayList();
		List<String> list3 = Lists.newArrayListWithCapacity(2);
		list3.add("1");
		list3.add("2");
		list3.add("3");
		System.out.println(list3.size());
	}

}
